/*
 * Copyright 2016 Andre Pfeiler
 *
 * This file is part of FindBugs-IDEA.
 *
 * FindBugs-IDEA is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FindBugs-IDEA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FindBugs-IDEA.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.twodividedbyzero.idea.findbugs.gui.settings;

import com.intellij.icons.AllIcons;
import com.intellij.ide.highlighter.XmlFileType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileSaverDescriptor;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.JDOMUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWrapper;
import com.intellij.util.xmlb.SmartSerializer;
import org.jdom.Document;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.twodividedbyzero.idea.findbugs.common.util.ErrorUtil;
import org.twodividedbyzero.idea.findbugs.common.util.IdeaUtilImpl;
import org.twodividedbyzero.idea.findbugs.common.util.IoUtil;
import org.twodividedbyzero.idea.findbugs.core.AbstractSettings;
import org.twodividedbyzero.idea.findbugs.core.ProjectSettings;
import org.twodividedbyzero.idea.findbugs.resources.ResourcesLoader;

import javax.swing.Icon;
import java.io.IOException;
import java.io.InputStream;

final class AdvancedSettingsAction extends DefaultActionGroup {

	@NotNull
	private final SettingsPane settingsPane;

	AdvancedSettingsAction(@NotNull final SettingsPane settingsPane) {
		super("Advanced Settings", true);
		this.settingsPane = settingsPane;
		getTemplatePresentation().setIcon(AllIcons.General.GearPlain);
		add(new ResetToDefault());
		add(new ImportSettings());
		add(new ExportSettings());
	}

	private class ResetToDefault extends AbstractAction {
		ResetToDefault() {
			super(
					"Reset To Default",
					"Set all settings to default",
					AllIcons.Actions.Reset_to_default
			);
		}

		@Override
		public void actionPerformed(@NotNull final AnActionEvent e) {
			settingsPane.reset(new ProjectSettings());
		}
	}

	private class ImportSettings extends AbstractAction {
		ImportSettings() {
			super(
					"Import",
					"Import Settings from file",
					AllIcons.ToolbarDecorator.Import
			);
		}

		@Override
		public void actionPerformed(@NotNull final AnActionEvent e) {
			final Project project = IdeaUtilImpl.getProject(e.getDataContext());
			final FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor();
			descriptor.setTitle(ResourcesLoader.getString("settings.choose.title"));
			descriptor.setDescription(ResourcesLoader.getString("settings.choose.description"));
			descriptor.withFileFilter(new Condition<VirtualFile>() {
				@Override
				public boolean value(final VirtualFile virtualFile) {
					return XmlFileType.DEFAULT_EXTENSION.equalsIgnoreCase(virtualFile.getExtension());
				}
			});

			final VirtualFile file = FileChooser.chooseFile(descriptor, settingsPane, project, null);
			if (file != null) {
				try {
					final InputStream in = file.getInputStream();
					try {
						final Element root = JDOMUtil.load(in);
						System.out.println(); // TODO
					} finally {
						IoUtil.safeClose(in);
					}
				} catch (final Exception ex) {
					throw ErrorUtil.toUnchecked(ex);
				}
			}
		}
	}

	private class ExportSettings extends AbstractAction {
		ExportSettings() {
			super(
					"Export",
					"Export Settings to file",
					AllIcons.Actions.Export
			);
		}

		@Override
		public void actionPerformed(@NotNull final AnActionEvent e) {

			final VirtualFileWrapper wrapper = FileChooserFactory.getInstance().createSaveFileDialog(
					new FileSaverDescriptor(
							ResourcesLoader.getString("settings.export.title"),
							ResourcesLoader.getString("settings.export.description"),
							XmlFileType.DEFAULT_EXTENSION
					), settingsPane).save(null, "FindBugs-IDEA");
			if (wrapper == null) {
				return;
			}

			final AbstractSettings settings = settingsPane.createSettings();
			try {
				settingsPane.apply(settings);
			} catch (final ConfigurationException ex) {
				Messages.showErrorDialog(settingsPane, ex.getMessage(), StringUtil.capitalizeWords(ResourcesLoader.getString("settings.invalid.title"), true));
			}

			Element root = new Element("findbugs");
			new SmartSerializer().writeExternal(settings, root, false);
			try {
				JDOMUtil.writeDocument(new Document(root), wrapper.getFile(), "\n");
			} catch (final IOException ex) {
				throw ErrorUtil.toUnchecked(ex);
			}
		}
	}

	private abstract class AbstractAction extends AnAction implements DumbAware {
		AbstractAction(@Nullable final String text, @Nullable final String description, @Nullable final Icon icon) {
			super(text, description, icon);
		}

		@Override
		public boolean isDumbAware() {
			return true;
		}
	}
}
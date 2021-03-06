package icons;

import com.intellij.icons.AllIcons;
import com.intellij.ui.LayeredIcon;
import com.intellij.util.IconUtil;
import org.twodividedbyzero.idea.findbugs.resources.ResourcesLoader;

import javax.swing.*;
import java.util.Map;

public interface PluginIcons {

    Icon FINDBUGS_ICON = ResourcesLoader.loadIcon("bug.png");
    Icon FINDBUGS_ICON_13X13 = ResourcesLoader.loadIcon("bug13x13.png");
    Icon FINDBUGS_CLOUD_ICON = ResourcesLoader.loadIcon("bug_cloud.png");

    Icon CLOSE_EDITOR_ICON = AllIcons.Actions.Close;
    Icon CLOSE_EDITOR_HOVER_ICON = AllIcons.Actions.CloseHovered;

    Icon INCLUDING_TESTS_MARK_ICON = IconUtil.flip(IconUtil.flip(AllIcons.Nodes.JunitTestMark , false), true);

    /**
     * --------------------------------------------------------------------------------------------------
     * Analyzing icons
     */
    Icon ANALYZE_SELECTED_FILE_ICON = LayeredIcon.create(
            ResourcesLoader.loadIcon("actions/analyzeSelectedFile.png"),
            AllIcons.Nodes.RunnableMark
    );
    Icon ANALYZE_CLASS_UNDER_CURSOR_ICON = LayeredIcon.create(AllIcons.Nodes.Class, AllIcons.Nodes.RunnableMark);
    Icon ANALYZE_PACKAGE_FILES_ICON = LayeredIcon.create(AllIcons.Nodes.Package, AllIcons.Nodes.RunnableMark);
    Icon ANALYZE_MODULE_FILES_NOT_INCLUDING_TESTS_ICON = LayeredIcon.create(AllIcons.Nodes.Module, AllIcons.Nodes.RunnableMark);
    Icon ANALYZE_MODULE_FILES_INCLUDING_TESTS_ICON = new LayeredIcon(
            AllIcons.Nodes.Module, AllIcons.Nodes.RunnableMark, INCLUDING_TESTS_MARK_ICON
    );
    Icon ANALYZE_PROJECT_FILES_NOT_INCLUDING_TESTS_ICON = LayeredIcon.create(AllIcons.Nodes.Project, AllIcons.Nodes.RunnableMark);
    Icon ANALYZE_PROJECT_FILES_INCLUDING_TESTS_ICON = new LayeredIcon(
            AllIcons.Nodes.Project, AllIcons.Nodes.RunnableMark, INCLUDING_TESTS_MARK_ICON
    );
    Icon ANALYZE_SCOPE_FILES_ICON = LayeredIcon.create(AllIcons.Ide.LocalScope, AllIcons.Nodes.RunnableMark);
    Icon ANALYZE_ALL_MODIFIED_FILES_ICON = LayeredIcon.create(AllIcons.Scope.ChangedFilesAll, AllIcons.Nodes.RunnableMark);
    Icon ANALYZE_CHANGELIST_FILES_ICON = LayeredIcon.create(AllIcons.Scope.ChangedFiles, AllIcons.Nodes.RunnableMark);


    /**
     * --------------------------------------------------------------------------------------------------
     * Grouping icons
     */
    Icon GROUP_BY_CATEGORY_ICON = LayeredIcon.create(
            ResourcesLoader.loadIcon("actions/groupByBugCategory.png"),
            ResourcesLoader.loadIcon("actions/groupByTemplate.png")
    );
    Icon GROUP_BY_CLASS_ICON = AllIcons.Actions.GroupByClass;
    Icon GROUP_BY_PRIORITY_ICON = AllIcons.Nodes.SortBySeverity;
    Icon GROUP_BY_PACKAGE_ICON = AllIcons.Actions.GroupByPackage;

    Icon GROUP_BY_RANK_SCARIEST_ICON = LayeredIcon.create(
            ResourcesLoader.loadIcon("priority/rankScariest.png"),
            ResourcesLoader.loadIcon("actions/groupByTemplate.png")
    );
    Icon GROUP_BY_RANK_SCARY_ICON = LayeredIcon.create(
            ResourcesLoader.loadIcon("priority/rankScary.png"),
            ResourcesLoader.loadIcon("actions/groupByTemplate.png")
    );
    Icon GROUP_BY_RANK_TROUBLING_ICON = LayeredIcon.create(
            ResourcesLoader.loadIcon("priority/rankTroubling.png"),
            ResourcesLoader.loadIcon("actions/groupByTemplate.png")
    );
    Icon GROUP_BY_RANK_OF_CONCERN_ICON = LayeredIcon.create(
            ResourcesLoader.loadIcon("priority/rankOfConcern.png"),
            ResourcesLoader.loadIcon("actions/groupByTemplate.png")
    );

    Map<String, Icon> GROUP_BY_RANK_ICONS = Map.of(
            "SCARIEST", GROUP_BY_RANK_SCARIEST_ICON,
            "SCARY", GROUP_BY_RANK_SCARY_ICON,
            "TROUBLING", GROUP_BY_RANK_TROUBLING_ICON,
            "OF_CONCERN", GROUP_BY_RANK_OF_CONCERN_ICON,
            "OF CONCERN", GROUP_BY_RANK_OF_CONCERN_ICON
    );

    Icon GROUP_BY_PRIORITY_HIGH_ICON = ResourcesLoader.loadIcon("priority/priorityHigh.png");
    Icon GROUP_BY_PRIORITY_MEDIUM_ICON = ResourcesLoader.loadIcon("priority/priorityMedium.png");
    Icon GROUP_BY_PRIORITY_LOW_ICON = ResourcesLoader.loadIcon("priority/priorityLow.png");
    Icon GROUP_BY_PRIORITY_EXP_ICON = ResourcesLoader.loadIcon("priority/priorityExp.png");
    Icon GROUP_BY_PRIORITY_IGNORE_ICON = ResourcesLoader.loadIcon("priority/priorityIgnore.png");

    Map<String, Icon> GROUP_BY_PRIORITY_ICONS = Map.of(
            "Low", GROUP_BY_PRIORITY_LOW_ICON,
            "Medium", GROUP_BY_PRIORITY_MEDIUM_ICON,
            "High", GROUP_BY_PRIORITY_HIGH_ICON,
            "Exp", GROUP_BY_PRIORITY_EXP_ICON,
            "Ignore", GROUP_BY_PRIORITY_IGNORE_ICON
    );

    /**
     * --------------------------------------------------------------------------------------------------
     * Priority icons
     */
    Icon HIGH_PRIORITY_ICON = ResourcesLoader.loadIcon("priority/bugHigh.png");
    Icon NORMAL_PRIORITY_ICON = ResourcesLoader.loadIcon("priority/bugNormal.png");
	Icon LOW_PRIORITY_ICON = ResourcesLoader.loadIcon("priority/bugLow.png");
    Icon EXP_PRIORITY_ICON = ResourcesLoader.loadIcon("priority/bugExp.png");

    /**
     * --------------------------------------------------------------------------------------------------
     * Tree icons
     */
    Icon TREENODE_OPEN_ICON = AllIcons.Nodes.Folder;
    Icon TREENODE_CLOSED_ICON = AllIcons.Nodes.Folder;

    /**
     * --------------------------------------------------------------------------------------------------
     * Navigation icons
     */
    Icon NAVIGATION_MOVEUP_ICON = AllIcons.Actions.MoveUp;
    Icon NAVIGATION_MOVEDOWN_ICON = AllIcons.Actions.MoveDown;
}

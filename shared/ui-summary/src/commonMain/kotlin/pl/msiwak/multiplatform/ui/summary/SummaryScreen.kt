package pl.msiwak.multiplatform.ui.summary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.ic_add
import athletetrack.shared.commonresources.generated.resources.no
import athletetrack.shared.commonresources.generated.resources.remove_category_dialog_description
import athletetrack.shared.commonresources.generated.resources.remove_category_dialog_title
import athletetrack.shared.commonresources.generated.resources.summary_add_category
import athletetrack.shared.commonresources.generated.resources.yes
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.ui.commonComponent.AdmobBanner
import pl.msiwak.multiplatform.ui.commonComponent.Loader
import pl.msiwak.multiplatform.ui.commonComponent.PopupDialog
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview
import pl.msiwak.multiplatform.ui.commonComponent.util.OnLifecycleEvent

@Composable
fun SummaryScreen(
    navController: NavController,
    viewModel: SummaryViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            viewModel.onResume()
        }
    }

    SummaryScreenContent(
        viewState = viewState,
        onUiAction = {
            when (it) {
                SummaryUiAction.OnAddCategoryClicked -> navController.navigate(NavDestination.AddCategoryDestination.NavAddCategoryScreen.route)
                is SummaryUiAction.OnCategoryClicked -> navController.navigate(NavDestination.CategoryDestination.NavCategoryScreen.route(it.id))
                else -> viewModel.onUiAction(it)
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SummaryScreenContent(
    viewState: State<SummaryState>,
    onUiAction: (SummaryUiAction) -> Unit
) {
    if (viewState.value.isRemoveCategoryDialogVisible) {
        PopupDialog(
            title = stringResource(Res.string.remove_category_dialog_title),
            description = stringResource(Res.string.remove_category_dialog_description),
            confirmButtonTitle = stringResource(Res.string.yes),
            dismissButtonTitle = stringResource(Res.string.no),
            onConfirmClicked = { onUiAction(SummaryUiAction.OnCategoryRemoved) },
            onDismissClicked = { onUiAction(SummaryUiAction.OnPopupDismissed) }
        )
    }

    if (viewState.value.isLoading) {
        Loader()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AdmobBanner(modifier = Modifier.padding(bottom = MaterialTheme.dimens.space_8))
        LazyColumn(
            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.space_16),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                itemsIndexed(viewState.value.categories) { index, category ->
                    CategoryItem(
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.dimens.space_8)
                            .combinedClickable(
                                enabled = true,
                                onClick = { onUiAction(SummaryUiAction.OnCategoryClicked(category.id)) },
                                onLongClick = { onUiAction(SummaryUiAction.OnCategoryLongClicked(index)) },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(
                                    color = Color.LightGray
                                )
                            ),
                        category = category
                    )
                }
                item {
                    Button(
                        modifier = Modifier.padding(MaterialTheme.dimens.space_16),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        onClick = { onUiAction(SummaryUiAction.OnAddCategoryClicked) }
                    ) {
                        Row {
                            Icon(
                                modifier = Modifier.padding(MaterialTheme.dimens.space_8),
                                painter = painterResource(Res.drawable.ic_add),
                                tint = MaterialTheme.colorScheme.tertiary,
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                text = stringResource(Res.string.summary_add_category),
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                }
            }
        )
    }
}

@DarkLightPreview
@Composable
fun SummaryScreenPreview() {
    AppTheme {
        SummaryScreenContent(MutableStateFlow(SummaryState()).collectAsState()) {}
    }
}

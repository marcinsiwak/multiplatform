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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.SR
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.ui.commonComponent.Loader
import pl.msiwak.multiplatform.ui.commonComponent.PopupDialog
import pl.msiwak.multiplatform.ui.commonComponent.extension.lifecycleObserver

@Composable
fun SummaryScreen(
    viewModel: SummaryViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        lifecycleObserver.collectLatest { event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> viewModel.onResume()
                else -> Unit
            }
        }
    }

    SummaryScreenContent(
        viewState = viewState,
        onCategoryRemoved = viewModel::onCategoryRemoved,
        onPopupDismissed = viewModel::onPopupDismissed,
        onCategoryClicked = viewModel::onCategoryClicked,
        onCategoryLongClicked = viewModel::onCategoryLongClicked,
        onAddCategoryClicked = viewModel::onAddCategoryClicked
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SummaryScreenContent(
    viewState: State<SummaryState>,
    onCategoryRemoved: () -> Unit = {},
    onPopupDismissed: () -> Unit = {},
    onCategoryClicked: (String) -> Unit = {},
    onCategoryLongClicked: (Int) -> Unit = {},
    onAddCategoryClicked: () -> Unit = {}
) {
    if (viewState.value.isRemoveCategoryDialogVisible) {
        PopupDialog(
            title = stringResource(SR.strings.remove_category_dialog_title),
            description = stringResource(SR.strings.remove_category_dialog_description),
            confirmButtonTitle = stringResource(SR.strings.yes),
            dismissButtonTitle = stringResource(SR.strings.no),
            onConfirmClicked = onCategoryRemoved,
            onDismissClicked = onPopupDismissed
        )
    }

    if (viewState.value.isLoading) {
        Loader()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
//        AdmobBanner(modifier = Modifier.padding(bottom = MaterialTheme.dimens.space_8))
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
                                onClick = { onCategoryClicked(category.id) },
                                onLongClick = { onCategoryLongClicked(index) },
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
                        onClick = { onAddCategoryClicked() }
                    ) {
                        Row {
                            Icon(
                                modifier = Modifier.padding(MaterialTheme.dimens.space_8),
                                painter = painterResource(SR.images.ic_add),
                                tint = MaterialTheme.colorScheme.tertiary,
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                text = stringResource(SR.strings.summary_add_category),
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                }
            }
        )
    }
}
//
// @Preview
// @Composable
// fun SummaryScreenPreview() {
//     AppTheme {
//         SummaryScreenContent(MutableStateFlow(SummaryState()).collectAsState())
//     }
// }

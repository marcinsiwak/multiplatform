package pl.msiwak.multiplatform.android.ui.summary

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.components.PopupDialog
import pl.msiwak.multiplatform.android.ui.loader.Loader
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.utils.OnLifecycleEvent
import pl.msiwak.multiplatform.commonResources.MR
import pl.msiwak.multiplatform.ui.summary.SummaryViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SummaryScreen() {
    val viewModel = koinViewModel<SummaryViewModel>()
    val viewState = viewModel.viewState.collectAsState()

    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> viewModel.onResume()
            else -> Unit
        }
    }

    if (viewState.value.isRemoveCategoryDialogVisible) {
        PopupDialog(
            title = stringResource(MR.strings.remove_category_dialog_title.resourceId),
            description = stringResource(MR.strings.remove_category_dialog_description.resourceId),
            confirmButtonTitle = stringResource(MR.strings.yes.resourceId),
            dismissButtonTitle = stringResource(MR.strings.no.resourceId),
            onConfirmClicked = {
                viewModel.onCategoryRemoved()
            },
            onDismissClicked = {
                viewModel.onPopupDismissed()
            }
        )
    }

    if (viewState.value.isLoading){
        Loader()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
                                onClick = { viewModel.onCategoryClicked(category.id) },
                                onLongClick = { viewModel.onCategoryLongClicked(index) },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(
                                    color = Color.LightGray
                                ),
                            ),
                        category = category
                    )
                }
                item {
                    Button(
                        modifier = Modifier.padding(MaterialTheme.dimens.space_16),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        onClick = { viewModel.onAddCategoryClicked() }
                    ) {
                        Row {
                            Icon(
                                modifier = Modifier.padding(MaterialTheme.dimens.space_8),
                                painter = painterResource(id = R.drawable.ic_add),
                                tint = MaterialTheme.colorScheme.tertiary,
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                text = stringResource(MR.strings.summary_add_category.resourceId),
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                }
            })
    }
}

@Preview
@Composable
fun SummaryScreenPreview() {
    SummaryScreen()
}
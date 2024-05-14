package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.ui.uikit.ComposeUIViewControllerDelegate
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.runBlocking
import pl.msiwak.multiplatform.ui.commonComponent.extension.lifecycleCallback

val defaultDelegateConfiguration: ComposeUIViewControllerDelegate =
    object : ComposeUIViewControllerDelegate {
        override fun viewDidAppear(animated: Boolean) {
            super.viewDidAppear(animated)
            lifecycleCallback(Lifecycle.Event.ON_RESUME)
        }

        override fun viewDidLoad() = runBlocking {
            super.viewDidLoad()
            lifecycleCallback(Lifecycle.Event.ON_CREATE)
        }

        override fun viewWillDisappear(animated: Boolean) {
            super.viewWillDisappear(animated)
            lifecycleCallback(Lifecycle.Event.ON_PAUSE)
        }

        override fun viewWillAppear(animated: Boolean) {
            super.viewWillAppear(animated)
            lifecycleCallback(Lifecycle.Event.ON_START)
        }

        override fun viewDidDisappear(animated: Boolean) {
            super.viewDidDisappear(animated)
            lifecycleCallback(Lifecycle.Event.ON_DESTROY)
        }
    }

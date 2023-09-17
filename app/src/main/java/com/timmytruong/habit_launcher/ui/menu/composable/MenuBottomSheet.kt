package com.timmytruong.habit_launcher.ui.menu.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.timmytruong.habit_launcher.data.AppInfo
import com.timmytruong.habit_launcher.ui.menu.MenuBottomSheetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBottomSheet(
    onDismissRequest: () -> Unit,
    appInfo: AppInfo? = null,
    viewModel: MenuBottomSheetViewModel = viewModel(),
    modifier: Modifier = Modifier,
) {
    val bottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = bottomSheetState,
        modifier = modifier,
    ) {
        MenuHeader(
            appInfo = appInfo,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Divider()
        MenuList(

        )
    }
}
package com.timmytruong.simplelauncher.ui.main.composable

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.timmytruong.simplelauncher.R

@Composable
internal fun AppIcon(
    modifier: Modifier = Modifier,
    drawable: Drawable,
    name: CharSequence,
) {
    return Image(
        modifier = modifier,
        painter = rememberDrawablePainter(drawable),
        contentDescription = "$name ${stringResource(id = R.string.app_icon)}"
    )
}
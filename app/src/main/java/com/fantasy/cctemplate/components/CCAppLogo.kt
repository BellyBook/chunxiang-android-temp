package com.fantasy.cctemplate.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fantasy.cctemplate.R
import com.fantasy.components.widget.PreviewScreen
import com.fantasy.components.aamedium.outerShadow
import com.fantasy.components.extension.compose.Image

@Composable
fun CCAppLogo(
    @DrawableRes id: Int = R.mipmap.ic_launcher,
    size: Int = 56,
    cornerRadius: Int = 4
) {
    Image(
        id = id,
        modifier = Modifier
            .outerShadow(
                shape = RoundedCornerShape(cornerRadius.dp),
                radius = 4
            )
            .clip(RoundedCornerShape(cornerRadius.dp))
            .size(size.dp),
        contentScale = ContentScale.Fit
    )
}

@Preview
@Composable
private fun preview() {
    PreviewScreen { }
    CCAppLogo()
}
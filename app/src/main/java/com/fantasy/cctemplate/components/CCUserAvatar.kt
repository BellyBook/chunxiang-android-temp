package com.fantasy.cctemplate.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fantasy.components.theme.CCColor
import com.fantasy.components.widget.PreviewScreen
import com.fantasy.components.widget.CCButton
import com.fantasy.components.widget.CCCoilImage

@Composable
fun CCUserAvatar(
    size: Int = 80,
    shape: Shape = CircleShape,
    borderWidth: Int = 6,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    CCButton(
        onClick =onClick
    ) {
        CCCoilImage(
            model = {  "https://pshxqdornijdcnzugaas.supabase.co/storage/v1/object/public/publicAssets/default_avatar.png"},
            modifier = modifier
                .clip(shape)
                .size(size.dp)
                .border(borderWidth.dp, CCColor.b1, shape)
                .padding(borderWidth.dp)

        )
    }
}

@Preview
@Composable
private fun preview() {
    PreviewScreen { }
    CCUserAvatar()
}
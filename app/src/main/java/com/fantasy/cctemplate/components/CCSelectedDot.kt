package com.fantasy.cctemplate.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fantasy.components.theme.CCColor
import com.fantasy.components.widget.PreviewScreen

@Composable
fun CCSelectedDot(
    isSelected: Boolean
) {
    if (isSelected) {
        Box(
            modifier = Modifier
                .background(CCColor.pink_t, CircleShape)
                .size(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .background(CCColor.f1, CircleShape)
                    .size(4.dp),
            )
        }
    } else {
        Box(
            modifier = Modifier
                .border(0.5.dp, CCColor.f2, CircleShape)
                .size(16.dp),
        )
    }
}

@Preview
@Composable
private fun preview() {
    PreviewScreen {
        CCSelectedDot(isSelected = true)
        CCSelectedDot(isSelected = false)
    }
}
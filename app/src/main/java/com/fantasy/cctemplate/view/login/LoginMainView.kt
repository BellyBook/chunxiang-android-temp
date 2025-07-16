package com.fantasy.cctemplate.view.login

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fantasy.components.base.BaseScreen
import com.fantasy.components.base.BaseViewModel
import com.fantasy.components.theme.CCFont
import com.fantasy.components.widget.CCScaffold


class LoginMainViewModel : BaseViewModel() {

}

class LoginMainView : BaseScreen() {
    @Composable
    override fun body() {
        CCScaffold(
        ) {
            Text(
                text = "登录页面",
                style = CCFont.big1b.v2,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    LoginMainView().Content()
}
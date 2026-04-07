package com.example.nutritionaltable.ui.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nutritionaltable.R
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme.sizing
import com.example.nutritionaltable.ui.theme.Secondary
import com.example.nutritionaltable.ui.theme.Typography


private const val NOTIFICATION_BADGE_CIRCLE_RADIUS_RATIO = 6
private const val NOTIFICATION_BADGE_CIRCLE_RADIUS_DIVIDER = 1.5f
private const val NOTIFICATION_BADGE_OFFSET_CIRCLE_X_RATIO = 0.7f
private const val NOTIFICATION_BADGE_OFFSET_CIRCLE_Y_RATIO = 0.2f
@Composable
fun WelcomeHeader(
    modifier: Modifier = Modifier,
    userName: String,
    hasNewNotification: Boolean = true,
    onNotificationClick: () -> Unit = {}
) {
    var hasNewNotification by remember { mutableStateOf(hasNewNotification) }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(sizing.x2l),
            painter = painterResource(R.drawable.img_male_profile),
            contentDescription = stringResource(R.string.imagem_perfil_usuario)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(sizing.sm)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.ola_usuario, userName),
                style = Typography.headlineMedium
            )
            Spacer(
                modifier = Modifier.height(sizing.x2)
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = " Seja bem vindo de volta",
                style = Typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSecondary)
            )
        }
        IconButton(
            onClick = {
                onNotificationClick()
                hasNewNotification = false
            },
        ) {
            Icon(
                modifier = Modifier
                    .size(sizing.lg)
                    .drawWithContent {
                        drawContent()

                        if (hasNewNotification) {
                            val circleRadius =
                                size.minDimension / NOTIFICATION_BADGE_CIRCLE_RADIUS_RATIO
                            drawCircle(
                                color = Color.White,
                                radius = circleRadius,
                                center = Offset(
                                    x = size.width * NOTIFICATION_BADGE_OFFSET_CIRCLE_X_RATIO,
                                    y = size.height * NOTIFICATION_BADGE_OFFSET_CIRCLE_Y_RATIO,
                                )
                            )

                            drawCircle(
                                color = Secondary,
                                radius = circleRadius / NOTIFICATION_BADGE_CIRCLE_RADIUS_DIVIDER,
                                center = Offset(
                                    x = size.width * NOTIFICATION_BADGE_OFFSET_CIRCLE_X_RATIO,
                                    y = size.height * NOTIFICATION_BADGE_OFFSET_CIRCLE_Y_RATIO,
                                )
                            )
                        }
                    },
                painter = painterResource(R.drawable.ic_bell),
                contentDescription = stringResource(R.string.botao_notificacoes)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeHeaderPreview() {
    NutritionalTableTheme {
        WelcomeHeader(
            modifier = Modifier.padding(sizing.md),
            userName = "Jonh Doe")
    }
}
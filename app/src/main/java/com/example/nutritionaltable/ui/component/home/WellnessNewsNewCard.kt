package com.example.nutritionaltable.ui.component.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutritionaltable.R
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme.sizing
import com.example.nutritionaltable.ui.theme.Typography

enum class WellnessNewsTag(
    val description: String
) {
    WELLNESS(description = "Bem-estar"),
    NUTRITION(description = "Nutrição"),
    FOOD_EDUCATION(description = "Educação alimentar")
}

data class WellnessNews(
    val title: String,
    @DrawableRes
    val imageRes: Int,
    val readTimeInMinutes: Int,
    val tags: List<WellnessNewsTag>
)

@Composable
fun WellnessNewsNewCard(
    modifier: Modifier = Modifier,
    wellnessNews: WellnessNews
) {

    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.imagem_noticia_saude_em_foco),
            painter = painterResource(wellnessNews.imageRes)
        )
        LazyRow(
            modifier = Modifier.padding(top = sizing.sm),
            horizontalArrangement = Arrangement.spacedBy(sizing.xs)
        ) {
            items(count = wellnessNews.tags.size) { index ->
                Text(
                    modifier = Modifier
                        .border(
                            width = sizing.x1,
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = RoundedCornerShape(size = sizing.xs)
                        )
                        .padding(horizontal = sizing.sm),
                    style = Typography.titleSmall.copy(fontSize = 12.sp),
                    text = wellnessNews.tags[index].description,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = sizing.md),
            style = Typography.titleSmall,
            text = wellnessNews.title,
            minLines = 3,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = stringResource(
                R.string.tempo_de_leitura_da_noticia,
                wellnessNews.readTimeInMinutes
            ),
            style = Typography.titleSmall.copy(color = MaterialTheme.colorScheme.onSecondary)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WellnessNewsNewCardPreview() {
    NutritionalTableTheme {
        val wellnessNews = WellnessNews(
            title = "Exemplo do título",
            readTimeInMinutes = 5,
            imageRes = R.drawable.img_nutritional_news_1,
            tags = listOf(
                WellnessNewsTag.WELLNESS,
                WellnessNewsTag.FOOD_EDUCATION
            )
        )
        WellnessNewsNewCard(
            modifier = Modifier
                .padding(sizing.md)
                .width(sizing.x4l),
            wellnessNews = wellnessNews
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WellnessNewsNewCardListPreview() {
    NutritionalTableTheme {
        val wellnessNews = WellnessNews(
            title = "Exemplo do título",
            readTimeInMinutes = 5,
            imageRes = R.drawable.img_nutritional_news_1,
            tags = listOf(
                WellnessNewsTag.WELLNESS,
                WellnessNewsTag.FOOD_EDUCATION
            )
        )
        LazyRow(
            modifier = Modifier.padding(sizing.md),
            horizontalArrangement = Arrangement.spacedBy(sizing.sm)
        ) {
            items(count = 3) {
                WellnessNewsNewCard(
                    modifier = Modifier.width(sizing.x4l),
                    wellnessNews = wellnessNews
                )
            }
        }
    }
}

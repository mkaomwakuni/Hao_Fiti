package iz.housing.haofiti.ui.theme.presentation.common

import android.annotation.SuppressLint
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ).value
    background(Color.LightGray.copy(alpha = alpha)) 
}

@Composable
fun ArticleCardShimmerEffect(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
    ) {
        // Box with shimmer effect and height 180 dp
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(MaterialTheme.shapes.small)
                .shimmerEffect()
        )

        // Spacer between Box and Row
        Spacer(modifier = Modifier.height(8.dp))

        // Row below the Box with shimmer effect
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small)
                    .height(50.dp)
                    .shimmerEffect()
            )
        }
    }
}
@Preview
@Composable
fun ShimmerEffect() {
    ArticleCardShimmerEffect()
}
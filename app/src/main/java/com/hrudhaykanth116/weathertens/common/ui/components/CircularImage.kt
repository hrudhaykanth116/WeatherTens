package com.hrudhaykanth116.weathertens.common.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hrudhaykanth116.weathertens.R
import com.hrudhaykanth116.weathertens.common.utils.compose.MyPreview

@MyPreview
@Composable
fun CircularImagePreview() {
    CircularImage(
        modifier = Modifier.size(40.dp),
        image = R.drawable.profile_icon,
        placeHolder = R.drawable.profile_icon,
        contentDescriptionResId = R.string.image_content_default_description,
    )
}

@Composable
fun CircularImage(
    modifier: Modifier = Modifier,
    // imageHolder: ImageHolder = ImageHolder.ImageVector(R.drawable.profile_icon),
    image: Any? = R.drawable.profile_icon,
    @DrawableRes placeHolder: Int = R.drawable.profile_icon,
    @StringRes contentDescriptionResId: Int = R.string.image_content_default_description,
    onClicked: () -> Unit = {},
) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        placeholder = painterResource(placeHolder),
        contentDescription = stringResource(contentDescriptionResId),
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .clip(CircleShape)
            .height(40.dp)
            .width(40.dp)
            .border(2.dp, Color.Gray, CircleShape).clickable {
                onClicked()
            }
    )

    // TODO: Add more image types and make more concise. 
    // val imageModifier = Modifier
    //     .size(64.dp)
    //     .clickable { onClicked() }
    //     .clip(CircleShape)
    //     .border(2.dp, Color.Gray, CircleShape)
    //
    // when (imageHolder) {
    //     is ImageHolder.Bitmap -> {
    //         Image(
    //             bitmap = imageHolder.imageBitmap,
    //             contentDescription = contentDescription,
    //             contentScale = ContentScale.Crop,
    //             modifier = imageModifier
    //         )
    //     }
    //     is ImageHolder.ImageVector -> {
    //         Image(
    //             painter = painterResource(id = imageHolder.resId),
    //             contentDescription = contentDescription,
    //             contentScale = ContentScale.Crop,
    //             modifier = imageModifier
    //         )
    //     }
    // }


}
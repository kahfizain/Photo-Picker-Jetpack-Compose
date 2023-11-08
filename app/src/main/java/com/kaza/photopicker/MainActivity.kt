package com.kaza.photopicker

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // MultiplePhotoPickerScreen()
            PhotoPickerScreen()
        }
    }
}

@Composable
fun PhotoPickerScreen() {

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val photoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                selectedImageUri = it
            })


    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Chose a Beautiful Photo", style = TextStyle(
                    fontSize = 24.sp, fontWeight = FontWeight.Medium, color = Color.Black
                )
            )

            Spacer(modifier = Modifier.size(20.dp))

            Button(modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0073E6), contentColor = Color.White
                ),
                onClick = {

                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)

                    )
                }) {

                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_input_add),
                        contentDescription = "Add Image"
                    )
                    Text(
                        text = "Pick a Photo", style = TextStyle(
                            fontSize = 18.sp
                        )
                    )
                }

            }

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = selectedImageUri,
                contentDescription = null
            )
        }
    }
}

@Composable
fun MultiplePhotoPickerScreen() {

    var selectedImageUris by remember {
        mutableStateOf<List<Uri?>>(emptyList())
    }

    val multiplePhotosPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickMultipleVisualMedia(
            maxItems = 2
        ), onResult = {
            selectedImageUris = it
        })


    Surface(
        modifier = Modifier.fillMaxSize()
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Choose some Beautiful Photos", style = TextStyle(
                    fontSize = 24.sp, fontWeight = FontWeight.Medium, color = Color.Black
                )
            )

            Spacer(modifier = Modifier.size(20.dp))

            Button(modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0073E6), contentColor = Color.White
                ),
                onClick = {
                    multiplePhotosPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {

                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_input_add),
                        contentDescription = "Add Image"
                    )
                    Text(
                        text = "Pick Photos", style = TextStyle(
                            fontSize = 18.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))

            LazyColumn {
                items(selectedImageUris) { selectedImageUri ->
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .padding(vertical = 4.dp),
                        model = selectedImageUri,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds

//                                In the code, we have something called AsyncImage, inside that, we have contentScale, you can change the values there with
//
//                                contentScale = ContentScale.Fit
//                                contentScale = ContentScale.Crop
//                                contentScale = ContentScale.Inside
//
//                                and use the one which suits
                    )
                }
            }

        }

    }
}


@Preview(showBackground = true)
@Composable
fun PhotoPickerScreenPreview() {
    // MultiplePhotoPickerScreen()
    PhotoPickerScreen()

}
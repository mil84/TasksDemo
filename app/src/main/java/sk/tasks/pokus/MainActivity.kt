package sk.tasks.pokus

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import sk.tasks.pokus.ui.theme.TasksDemoTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TasksDemoTheme {
                MainScreen {
                    AuthClient.onAuthStart(this@MainActivity)
                }
            }
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AuthClient.REQUEST_AUTH_KEY) {
            AuthClient.onAuthResult(this, data)
        }
    }
}

@Composable
fun MainScreen(onAuth: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { onAuth() }) {
            Text(text = "Authorize")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TasksDemoTheme {
        MainScreen {
            // nothing (preview)
        }
    }
}
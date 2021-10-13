package dev.gressier.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.gressier.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = listOf("Android", "There")) {
    var counterState by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        NamesList(names = names, modifier = Modifier.weight(1f))

        Counter(count = counterState, updateCount = { newCount -> counterState = newCount })
        if (counterState > 5) {
            Text(text = "I ❤️ to count!")
        }
    }
}

@Composable
fun NamesList(names: List<String>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        names.forEach { name ->
            Greeting(name = name)
            Divider()
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeBasicsTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = { updateCount(count + 1) }) {
        Text(text = "I've been clicked $count times")
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        "Hello $name!",
        Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}
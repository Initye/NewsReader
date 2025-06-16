package com.example.newsreader.ui.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsreader.ui.ApiViewModel
import com.example.newsreader.ui.theme.HorizontalDivider
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavDrawer(navController: NavController, modifier: Modifier = Modifier,viewModel: ApiViewModel, content: @Composable (onMenuClick: () -> Unit) -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val menuClickHandler: () -> Unit = {
        scope.launch {
            if (drawerState.isClosed) {
                drawerState.open()
            } else {
                drawerState.close()
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.White,
                modifier = modifier
                    .width(200.dp),
            ) {
                Text(
                    "News Categories",
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 4.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
                HorizontalDivider(thickness = 2.dp, color = HorizontalDivider)
                LazyColumn {
                    items(viewModel.categoriesList) { category ->
                        NavigationDrawerItem(
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.White,
                                selectedTextColor = Color.Black,
                                selectedContainerColor = Color.Transparent,
                            ),
                            label = { Text(category) },
                            selected = viewModel.selectedCategory.value == category, //No need for that Drawer
                            onClick = {
                                scope.launch{
                                    drawerState.close() //So that the drawer is not added to stack in navigation
                                }
                                viewModel.selectCategory(category)
                                navController.navigate("categoryNews")

                            }
                        )
                    }
                }
            }
        }
        ) {
        content(menuClickHandler)
    }
}



@Preview
@Composable
fun NavDrawerPreview() {
    NavDrawer(
        navController = TODO(),
        modifier = TODO(),
        content = TODO(),
        viewModel = TODO()
    )
}


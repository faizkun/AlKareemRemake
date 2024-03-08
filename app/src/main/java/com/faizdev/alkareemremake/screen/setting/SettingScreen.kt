package com.faizdev.alkareemremake.screen.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faizdev.alkareemremake.data.kotpref.SettingPreference
import com.faizdev.alkareemremake.destinations.Destination
import com.faizdev.alkareemremake.screen.read.GlobalViewModel
import com.faizdev.alkareemremake.screen.readsurah.ReadArguments
import com.faizdev.alkareemremake.ui.theme.poppins
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@com.ramcosta.composedestinations.annotation.Destination
@Composable
fun SettingScreen(
    navigator: DestinationsNavigator
) {

    Scaffold(

        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.navigateUp()
                    }) {

                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "",
                            modifier = Modifier.size(55.dp)

                        )

                    }
                },

                title = {
                    Text(
                        text = "Settings",
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp


                    )

                })
        }


    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {

            var expandedLanguage by remember { mutableStateOf(false) }

            var selectedLanguange by remember {
                mutableStateOf(
                    if (SettingPreference.currentLanguage == SettingPreference.INDONESIA) "Indonesia"
                    else "English"
                )
            }

            var expanded by remember { mutableStateOf(false) }
            var selectedText by remember {
                mutableStateOf(
                    SettingPreference
                        .listQari[SettingPreference.currentQari]
                        .qariName
                )
            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Qari"
                )
                Box() {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        TextField(
                            value = selectedText,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier.menuAnchor()

                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },

                            ) {

                            SettingPreference.listQari.forEachIndexed { index, qori ->
                                DropdownMenuItem(
                                    text = { Text(text = qori.qariName) },
                                    onClick = {
                                        selectedText = qori.qariName
                                        expanded = !expanded
                                        SettingPreference.currentQari = index
                                    },

                                    )
                            }

                        }

                    }
                }


            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(text = "Bahasa")

                Box() {
                    ExposedDropdownMenuBox(
                        expanded = expandedLanguage,
                        onExpandedChange = { expandedLanguage = !expandedLanguage }
                    ) {
                        TextField(
                            value = selectedLanguange,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier.menuAnchor()

                        )

                        ExposedDropdownMenu(
                            expanded = expandedLanguage,
                            onDismissRequest = { expandedLanguage = expandedLanguage!! },

                            ) {


                            DropdownMenuItem(
                                text = { Text(text = "English") },
                                onClick = {
                                    expandedLanguage = !expandedLanguage
                                    selectedLanguange = "English"
                                },



                                )

                            DropdownMenuItem(
                                text = { Text(text = "Indonesia") },
                                onClick = {
                                    expandedLanguage = !expandedLanguage
                                    selectedLanguange = "Indonesia"
                                },



                                )

                        }

                    }

                }


            }

        }

    }


}





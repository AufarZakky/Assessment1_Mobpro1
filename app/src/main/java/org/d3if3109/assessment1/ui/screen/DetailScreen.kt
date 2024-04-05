package org.d3if3109.assessment1.ui.screen

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.d3if3109.assessment1.R
import kotlin.math.pow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, jenis: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.challenge_intro))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { padding ->
        DetailContent(
            jenis = jenis,
            modifier = Modifier.padding(padding),
            inputKosongMessage = String()
        )
    }
}


@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}

@Composable
fun DetailContent(jenis: String, modifier: Modifier = Modifier, inputKosongMessage: String) {
    var sisi by rememberSaveable { mutableStateOf("") }
    var sisiErr by rememberSaveable { mutableStateOf(false) }
    var jariJari by rememberSaveable { mutableStateOf("") }
    var jariJariErr by rememberSaveable { mutableStateOf(false) }
    var panjang by rememberSaveable { mutableStateOf("") }
    var panjangErr by rememberSaveable { mutableStateOf(false) }
    var lebar by rememberSaveable { mutableStateOf("") }
    var lebarErr by rememberSaveable { mutableStateOf(false) }
    var tinggi by rememberSaveable { mutableStateOf("") }
    var tinggiErr by rememberSaveable { mutableStateOf(false) }

    var hasil by rememberSaveable { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    var hitungClicked by remember { mutableStateOf(false) }
    var hitungVolume by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (jenis) {
            "kubus" -> {
                Text(
                    text = stringResource(R.string.kubus),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = sisi,
                    onValueChange = { newValue ->
                        if (newValue.isNotEmpty() && newValue.toFloatOrNull() != null && newValue.toFloat() > 0) {
                            sisi = newValue
                            sisiErr = false
                        } else {
                            sisi = newValue.takeWhile { it.isDigit() || it == '.' }
                            sisiErr = true
                        }
                    },
                    label = { Text(text = stringResource(id = R.string.sisi)) },
                    isError = sisiErr,
                    trailingIcon = { IconPicker(sisiErr, "cm") },
                    supportingText = { ErrorHint(sisiErr) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = hitungVolume,
                        onClick = { hitungVolume = true },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = stringResource(id = R.string.buttonvolume))
                    Spacer(modifier = Modifier.width(10.dp))
                    RadioButton(
                        selected = !hitungVolume,
                        onClick = { hitungVolume = false },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = stringResource(id = R.string.buttonluas))
                }
                Button(
                    onClick = {
                        if (sisi.isEmpty()) {
                            errorMessage = inputKosongMessage
                            return@Button
                        } else {
                            if (!sisiErr) {
                                if (hitungVolume) {
                                    hasil = (sisi.toFloat().pow(3)).toString()
                                } else {
                                    hasil = (6 * sisi.toFloat().pow(2)).toString()
                                }
                                hitungClicked = true
                            }
                        }
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = stringResource(R.string.hitung))
                }
            }

            "tabung" -> {
                Text(
                    text = stringResource(R.string.tabung),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = jariJari,
                    onValueChange = { newValue ->
                        if (newValue.isNotEmpty() && newValue.toFloatOrNull() != null && newValue.toFloat() > 0) {
                            jariJari = newValue
                            jariJariErr = false
                        } else {
                            jariJari = newValue.takeWhile { it.isDigit() || it == '.' }
                            jariJariErr = true
                        }
                    },
                    label = { Text(text = stringResource(id = R.string.jarijari)) },
                    isError = jariJariErr,
                    trailingIcon = { IconPicker(jariJariErr, "cm") },
                    supportingText = { ErrorHint(jariJariErr) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = tinggi,
                    onValueChange = { newValue ->
                        if (newValue.isNotEmpty() && newValue.toFloatOrNull() != null && newValue.toFloat() > 0) {
                            tinggi = newValue
                            tinggiErr = false
                        } else {
                            tinggi = newValue.takeWhile { it.isDigit() || it == '.' }
                            tinggiErr = true
                        }
                    },
                    label = { Text(text = stringResource(id = R.string.tinggi)) },
                    isError = tinggiErr,
                    trailingIcon = { IconPicker(tinggiErr, "cm") },
                    supportingText = { ErrorHint(tinggiErr) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = hitungVolume,
                        onClick = { hitungVolume = true },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = stringResource(id = R.string.buttonvolume))
                    Spacer(modifier = Modifier.width(10.dp))
                    RadioButton(
                        selected = !hitungVolume,
                        onClick = { hitungVolume = false },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = stringResource(id = R.string.buttonluas))
                }
                Button(
                    onClick = {
                        if (jariJari.isEmpty() || tinggi.isEmpty()) {
                            errorMessage = inputKosongMessage
                            return@Button
                        } else {
                            if (!jariJariErr && !tinggiErr) {
                                if (hitungVolume) {
                                    hasil = (Math.PI * jariJari.toFloat()
                                        .pow(2) * tinggi.toFloat()).toString()
                                } else {
                                    hasil =
                                        (2 * Math.PI * jariJari.toFloat() * (jariJari.toFloat() + tinggi.toFloat())).toString()
                                }
                                hitungClicked = true
                            }
                        }
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = stringResource(R.string.hitung))
                }
            }

            "balok" -> {
                Text(
                    text = stringResource(R.string.balok),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = panjang,
                    onValueChange = { newValue ->
                        if (newValue.isNotEmpty() && newValue.toFloatOrNull() != null && newValue.toFloat() > 0) {
                            panjang = newValue
                            panjangErr = false
                        } else {
                            panjang = newValue.takeWhile { it.isDigit() || it == '.' }
                            panjangErr = true
                        }
                    },
                    label = { Text(text = stringResource(id = R.string.panjang)) },
                    isError = panjangErr,
                    trailingIcon = { IconPicker(panjangErr, "cm") },
                    supportingText = { ErrorHint(panjangErr) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = lebar,
                    onValueChange = { newValue ->
                        if (newValue.isNotEmpty() && newValue.toFloatOrNull() != null && newValue.toFloat() > 0) {
                            lebar = newValue
                            lebarErr = false
                        } else {
                            lebar = newValue.takeWhile { it.isDigit() || it == '.' }
                            lebarErr = true
                        }
                    },
                    label = { Text(text = stringResource(id = R.string.lebar)) },
                    isError = lebarErr,
                    trailingIcon = { IconPicker(lebarErr, "cm") },
                    supportingText = { ErrorHint(lebarErr) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = tinggi,
                    onValueChange = { newValue ->
                        if (newValue.isNotEmpty() && newValue.toFloatOrNull() != null && newValue.toFloat() > 0) {
                            tinggi = newValue
                            tinggiErr = false
                        } else {
                            tinggi = newValue.takeWhile { it.isDigit() || it == '.' }
                            tinggiErr = true
                        }
                    },
                    label = { Text(text = stringResource(id = R.string.tinggi)) },
                    isError = tinggiErr,
                    trailingIcon = { IconPicker(tinggiErr, "cm") },
                    supportingText = { ErrorHint(tinggiErr) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = hitungVolume,
                        onClick = { hitungVolume = true },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = stringResource(id = R.string.buttonvolume))
                    Spacer(modifier = Modifier.width(10.dp))
                    RadioButton(
                        selected = !hitungVolume,
                        onClick = { hitungVolume = false },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = stringResource(id = R.string.buttonluas))
                }
                Button(
                    onClick = {
                        if (panjang.isEmpty() || lebar.isEmpty() || tinggi.isEmpty()) {
                            errorMessage = inputKosongMessage
                            return@Button
                        } else {
                            if (!panjangErr && !lebarErr && !tinggiErr) {
                                if (hitungVolume) {
                                    hasil =
                                        (panjang.toFloat() * lebar.toFloat() * tinggi.toFloat()).toString()
                                } else {
                                    hasil =
                                        ((2 * panjang.toFloat() * lebar.toFloat()) + (2 * panjang.toFloat() * tinggi.toFloat()) + (2 * lebar.toFloat() * tinggi.toFloat())).toString()
                                }
                                hitungClicked = true
                            }
                        }
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = stringResource(R.string.hitung))
                }
            }

            "kosong" -> {
                Toast.makeText(context, stringResource(R.string.x_diklik), Toast.LENGTH_SHORT)
                    .show()
            }

        }
        if (hitungClicked && jenis != "kosong") {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(R.string.volume, hasil.toFloatOrNull() ?: 0f))

            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.getString(
                            R.string.bagikan_template,
                            hasil
                        )
                    )
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.bagikan))
            }
        }
    }
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

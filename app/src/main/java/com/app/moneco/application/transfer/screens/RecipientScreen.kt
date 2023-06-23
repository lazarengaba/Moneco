package com.app.moneco.application.transfer.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.Contacts
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.moneco.R
import com.app.moneco.application.transfer.viewModels.RecipientViewModel
import com.app.moneco.common.screens.NavScreen
import com.app.moneco.handlers.outfitRegular
import com.app.moneco.handlers.outfitSemiBold
import com.app.moneco.models.Country
import com.app.moneco.models.Recipient
import com.app.moneco.ui.theme.MonecoTheme
import com.app.moneco.utilities.NAME_MAX_LENGTH
import com.app.moneco.utilities.NAME_MIN_LENGTH
import com.app.moneco.utilities.PHONE_NUMBER_MAX_LENGTH
import com.app.moneco.utilities.PHONE_NUMBER_MIN_LENGTH

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipientScreenView(navController: NavHostController) {
    var recipient by remember { mutableStateOf("prev") }
    val searchText = remember { mutableStateOf("") }
    val selectedCountryID = remember { mutableIntStateOf(0) }
    val selectedCountryCurrencyCode = remember { mutableStateOf("") }
    val selectedCountryCode = remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    MonecoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.main_padding_size),
                            end = dimensionResource(id = R.dimen.main_padding_size)
                        )
                ) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.main_spacer_size)),
                        verticalArrangement = Arrangement.Center
                    ) {
                        FloatingActionButton(
                            onClick = {
                                navController.navigateUp()
                            },
                            containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f),
                            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                            modifier = Modifier
                                .width(dimensionResource(id = R.dimen.small_icon_cover_size))
                                .height(dimensionResource(id = R.dimen.small_icon_cover_size)),
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.small_icon_cover_rounded_size))
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = stringResource(id = R.string.close)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))
                }
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .weight(1f)
                ) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_plus_spacer_size)))

                    Text(
                        text = "Who are you sending to?",
                        fontFamily = outfitSemiBold,
                        fontSize = 26.sp,
                        modifier = Modifier
                            .padding(
                                start = dimensionResource(id = R.dimen.middle_spacer_size),
                                end = dimensionResource(id = R.dimen.middle_spacer_size)
                            )
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle_spacer_size)))

                    Box(
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.main_spacer_size))
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(id = R.dimen.middle_spacer_size),
                                end = dimensionResource(id = R.dimen.middle_spacer_size)
                            )
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(dimensionResource(id = R.dimen.small_plus_spacer_size))
                            )
                    ) {
                        Row {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .background(
                                        color = if (recipient == "prev") MaterialTheme.colorScheme.primary else Color.Transparent,
                                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.small_plus_spacer_size))
                                    )
                                    .weight(1f)
                                    .clickable(
                                        onClick = {
                                            recipient = "prev"
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Previous recipients",
                                    color = if (recipient == "prev") MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary,
                                    fontFamily = outfitSemiBold,
                                    modifier = Modifier
                                        .padding(dimensionResource(id = R.dimen.small_plus_spacer_size))
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .background(
                                        color = if (recipient == "new") MaterialTheme.colorScheme.primary else Color.Transparent,
                                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.small_plus_spacer_size))
                                    )
                                    .weight(1f)
                                    .clickable(
                                        onClick = {
                                            recipient = "new"
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "New recipient",
                                    color = if (recipient == "new") MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary,
                                    fontFamily = outfitSemiBold,
                                    modifier = Modifier
                                        .padding(dimensionResource(id = R.dimen.small_plus_spacer_size))
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.middle_spacer_size)))

                    AnimatedVisibility(visible = recipient == "prev") {
                        Column {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(dimensionResource(id = R.dimen.medium_icon_cover_size))
                                    .padding(
                                        start = dimensionResource(id = R.dimen.middle_spacer_size),
                                        end = dimensionResource(id = R.dimen.middle_spacer_size)
                                    )
                                    .background(
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.small_plus_icon_cover_rounded_size))
                                    )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(start = dimensionResource(id = R.dimen.main_padding_size)),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Search,
                                        contentDescription = "Search"
                                    )

                                    BasicTextField(
                                        value = searchText.value,
                                        onValueChange = { newText ->
                                            searchText.value = newText
                                        },
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(
                                                top = dimensionResource(id = R.dimen.small_plus_spacer_size),
                                                bottom = dimensionResource(id = R.dimen.small_plus_spacer_size)
                                            ),
                                        singleLine = true
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_icon_cover_size)))

                            RecipientList(
                                navController = navController,
                                searchText = searchText
                            )
                        }
                    }

                    AnimatedVisibility(visible = recipient == "new") {

                        val viewModel: RecipientViewModel = viewModel()

                        val countries by viewModel.countries.observeAsState(emptyList())

                        LaunchedEffect(Unit) {
                            viewModel.fetchCountries()
                        }

                        Column {

                            Column(
                                modifier = Modifier
                                    .padding(
                                        start = dimensionResource(id = R.dimen.main_padding_size),
                                        end = dimensionResource(id = R.dimen.main_padding_size)
                                    )
                            ) {
                                Text(
                                    text = "Country",
                                    fontFamily = outfitRegular
                                )

                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_plus_spacer_size)))

                                DropDownCountries(
                                    selectedCountryID = selectedCountryID,
                                    selectedCountryCurrency = selectedCountryCurrencyCode,
                                    selectedCountryCode = selectedCountryCode,
                                    countries = countries
                                )

                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                                OutlinedCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(dimensionResource(id = R.dimen.main_spacer_size)),
                                    border = BorderStroke(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                                    )
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(MaterialTheme.colorScheme.surfaceVariant)
                                            .clickable(
                                                onClick = {

                                                }
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Row {
                                            Icon(
                                                imageVector = Icons.Rounded.Contacts,
                                                contentDescription = "Contact icon",
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_plus_spacer_size)))
                                            Text(
                                                text = "Choose a contact",
                                                fontFamily = outfitSemiBold,
                                                fontSize = 16.sp,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_plus_icon_cover_size)))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Divider(
                                        modifier = Modifier
                                            .weight(1f),
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
                                    )
                                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_plus_spacer_size)))

                                    Text(
                                        text = "OR ADD MANUALLY",
                                        fontFamily = outfitRegular,
                                        fontSize = 14.sp,
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                                    )

                                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_plus_spacer_size)))
                                    Divider(
                                        modifier = Modifier
                                            .weight(1f),
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
                                    )
                                }

                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                                Text(
                                    text = "Phone number",
                                    fontFamily = outfitRegular
                                )

                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_spacer_size)))

                                OutlinedTextField(
                                    value = phoneNumber,
                                    onValueChange = { newValue ->
                                        if (newValue.length <= PHONE_NUMBER_MAX_LENGTH) {
                                            phoneNumber = newValue
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    placeholder = {
                                        Text(
                                            text = "Enter phone number",
                                            fontFamily = outfitRegular
                                        )
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.small_spacer_size))
                                )

                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                                Text(
                                    text = "First name",
                                    fontFamily = outfitRegular
                                )

                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_spacer_size)))

                                OutlinedTextField(
                                    value = firstName,
                                    onValueChange = { newValue ->
                                        if (newValue.length <= NAME_MAX_LENGTH)
                                            firstName = newValue
                                    },
                                    maxLines = 1,
                                    modifier = Modifier.fillMaxWidth(),
                                    placeholder = {
                                        Text(
                                            text = "Enter first name",
                                            fontFamily = outfitRegular
                                        )
                                    },
                                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.small_spacer_size))
                                )

                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                                Text(
                                    text = "Last name",
                                    fontFamily = outfitRegular
                                )

                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_spacer_size)))

                                OutlinedTextField(
                                    value = lastName,
                                    onValueChange = { newValue ->
                                        if (newValue.length <= NAME_MAX_LENGTH)
                                            lastName = newValue
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    placeholder = {
                                        Text(
                                            text = "Enter last name",
                                            fontFamily = outfitRegular
                                        )
                                    },
                                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.small_spacer_size))
                                )

                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.country_card_height)))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.mobile_wallet_button_height)))
                }

                if (recipient == "new") {
                    Divider(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
                    )

                    Column(
                        modifier = Modifier.padding(
                            start = dimensionResource(id = R.dimen.main_padding_size),
                            end = dimensionResource(id = R.dimen.main_padding_size)
                        )
                    ) {

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                        ElevatedButton(
                            onClick = {
                                navController.navigate(
                                    NavScreen.MobileWallet.route + "/${selectedCountryCurrencyCode.value}/${selectedCountryCode.value}/$phoneNumber/$firstName $lastName")
                            },
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .height(dimensionResource(id = R.dimen.country_card_height))
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.main_padding_size)),
                            enabled = phoneNumber.length >= PHONE_NUMBER_MIN_LENGTH
                                    && firstName.length >= NAME_MIN_LENGTH
                                    && lastName.length >= NAME_MIN_LENGTH
                        ) {
                            Text(
                                text = "Continue", fontFamily = outfitSemiBold, fontSize = 16.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownCountries(
    selectedCountryID: MutableState<Int>,
    selectedCountryCurrency: MutableState<String>,
    selectedCountryCode: MutableState<String>,
    countries: List<Country>
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded) {
        Icons.Rounded.KeyboardArrowUp
    } else {
        Icons.Rounded.KeyboardArrowDown
    }

    val countriesFlag = listOf(
        R.drawable.tg,
        R.drawable.td,
        R.drawable.tg,
        R.drawable.td,
    )

    Column(modifier = Modifier.fillMaxWidth()) {

        OutlinedCard(
            onClick = {
                expanded = !expanded
            },
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.country_card_height))
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                }
        ) {

            if (countries.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(dimensionResource(id = R.dimen.medium_plus_icon_size))
                            .height(dimensionResource(id = R.dimen.medium_plus_icon_size))
                    )
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                        .padding(
                            start = dimensionResource(id = R.dimen.main_padding_size),
                            end = dimensionResource(id = R.dimen.main_padding_size)
                        )
                ) {

                    Image(
                        painterResource(
                            id = if (selectedCountryID.value == 0)
                                countriesFlag[0]
                            else
                                countriesFlag[selectedCountryID.value - 1]
                        ),
                        contentDescription = "Country flag",
                        modifier = Modifier
                            .width(dimensionResource(id = R.dimen.middle_spacer_size))
                            .height(dimensionResource(id = R.dimen.middle_spacer_size)),
                        contentScale = ContentScale.FillBounds
                    )

                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.main_padding_size)))

                    Text(
                        text = if (selectedCountryID.value == 0)
                            countries[0].name
                        else
                            countries[selectedCountryID.value - 1].name,
                        fontFamily = outfitRegular,
                        modifier = Modifier
                            .weight(1f)
                    )

                    Text(
                        text = "+228",
                        fontFamily = outfitRegular
                    )

                    Icon(
                        icon,
                        contentDescription = "Expand arrow",
                        Modifier.clickable { expanded = !expanded })
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.middle_spacer_size),
                    end = dimensionResource(id = R.dimen.middle_spacer_size)
                )
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current){textFieldSize.width.toDp()})
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painterResource(id = countriesFlag[country.id.toInt() - 1]),
                                    contentDescription = "${country.name} flag",
                                    modifier = Modifier
                                        .width(dimensionResource(id = R.dimen.middle_spacer_size))
                                        .height(dimensionResource(id = R.dimen.middle_spacer_size)),
                                    contentScale = ContentScale.FillBounds
                                )

                                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.main_padding_size)))

                                Text(
                                    text = country.name,
                                    fontFamily = outfitRegular,
                                    modifier = Modifier
                                        .weight(1f)
                                )

                                Text(
                                    text = "+228",
                                    fontFamily = outfitRegular
                                )
                            }
                        },
                        onClick = {
                            selectedCountryID.value = country.id.toInt()
                            selectedCountryCurrency.value = country.currencyCode
                            selectedCountryCode.value = "+${country.id}"
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun RecipientList(
    navController: NavHostController,
    searchText: MutableState<String>
) {
    val viewModel: RecipientViewModel = viewModel()

    val recipients by viewModel.recipients.observeAsState(emptyList())
//    val countries by viewModel.countries.observeAsState(emptyList())
    val isFetched by viewModel.isRecipientsFetched.observeAsState(false)

    LaunchedEffect(Unit) {
        viewModel.fetchRecipients()
        viewModel.fetchCountries()
    }

    val recipientImages = listOf(
        R.drawable.user_one,
        R.drawable.user_two,
        R.drawable.user_three
    )

    val filteredRecipients: List<Recipient> = if (searchText.value.isEmpty()) {
        recipients
    } else {
        val resultList = ArrayList<Recipient>()
        for (recipient in recipients) {
            if (recipient.name.lowercase().contains(searchText.value.lowercase())) {
                resultList.add(recipient)
            }
        }
        resultList
    }

    val phoneNumber = "12545695"
    val countryCode = "+229"
    val currencyCode = "+229"

    if (filteredRecipients.isEmpty() && searchText.value.isNotEmpty()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painterResource(id = R.drawable.no_result),
                    contentDescription = "No result",
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.success_icon_size))
                        .height(dimensionResource(id = R.dimen.success_icon_size)),
                    contentScale = ContentScale.FillBounds
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding_size)))

                Text(
                    text = "No matching result found.",
                    fontFamily = outfitSemiBold,
                    fontSize = 18.sp
                )
            }
        }
    } else {

        Text(
            text = "Contacts on your phone",
            fontFamily = outfitSemiBold,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.middle_spacer_size),
                    end = dimensionResource(id = R.dimen.middle_spacer_size)
                )
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_plus_spacer_size)))



        if (!isFetched) {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_icon_cover_size)))
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        filteredRecipients.forEach { recipient ->
            Box(
                Modifier.clickable(
                    onClick = {
                        navController.navigate(NavScreen.SendMoney.route + "/$currencyCode/$countryCode/$phoneNumber/${recipient.name}/${recipient.mobileWallet}")
                    }
                )
            ) {
                Row(
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.medium_list_item_height))
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(id = R.dimen.main_padding_size),
                            end = dimensionResource(id = R.dimen.main_padding_size),
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(dimensionResource(id = R.dimen.medium_plus_icon_cover_size))
                            .height(dimensionResource(id = R.dimen.medium_plus_icon_cover_size))
                            .background(
                                color = MaterialTheme.colorScheme.onBackground.copy(
                                    alpha = 0.25f
                                ),
                                shape = RoundedCornerShape(dimensionResource(id = R.dimen.small_plus_icon_cover_rounded_size))
                            ),
                    ) {
                        Image(
                            painterResource(id = recipientImages[recipient.id.toInt() - 1]),
                            contentDescription = recipient.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.main_padding_size)))

                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = recipient.name,
                            fontFamily = outfitSemiBold,
                            fontSize = 16.sp,
                        )
                        Text(
                            text = "$countryCode $phoneNumber",
                            fontFamily = outfitRegular,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                    }

                    Icon(
                        imageVector = Icons.Rounded.ArrowForwardIos,
                        contentDescription = "Arrow icon",
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.main_padding_size))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipientScreenPreview() {
    RecipientScreenView(navController = rememberNavController())
}
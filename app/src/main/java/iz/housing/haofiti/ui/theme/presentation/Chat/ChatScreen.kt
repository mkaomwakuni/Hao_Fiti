package iz.housing.haofiti.ui.theme.presentation.Chat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactForm() {
//    var name by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var phone by remember { mutableStateOf("") }
//    var message by remember { mutableStateOf("") }
//    var lastname by remember { mutableStateOf("") }
//    var residence by remember { mutableStateOf("") }
//    var isAgreed by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Contact the Agent") },
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Navigate Back")
                    }
                }
            )
        }
    ){
        innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ){
            Text("Your Contact Details", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.padding(16.dp))
            CustomTextField(
                value = name,
                onValueChange = {name = it},
                label = "First Name",
                placeholder = "Enter Your First Name"
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(
                value = lastname,
                onValueChange = {lastname = it},
                label = "Last Name",
                placeholder = "Enter Your Last Name"
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(
                value = email,
                onValueChange = {email = it},
                label = "Email",
                placeholder = "Enter Your Email"
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(
                value = residence,
                onValueChange = {residence = it},
                label = "Residence",
                placeholder = "Residential Town/ City"
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomPhoneField(
                phone = phone,
                onPhoneChange = {phone = it}
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                value = message,
                onValueChange = {message = it},
                label = "Message",
                placeholder = "Leave Your Enquaries"
            )
            Spacer(modifier = Modifier.height(80.dp))
            CustomCheckBox(
                checked = isAgreed,
                onCheckedChange = {isAgreed = it},
                label = "I agree to the terms and conditions"
            )
            Spacer(modifier = Modifier.height(30.dp))
            CustomTextButton(
                text = "Send Message",
                onClick = { /*TODO*/ }
            )
        }
    }
}@Composable
fun CustomTextField(
    value: String,
    onValueChange:(String) ->Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(label)},
        placeholder = { Text(placeholder) },
        modifier = modifier
            .fillMaxWidth()
            .border(border = BorderStroke( 2.dp, MaterialTheme.colorScheme.primary)),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        )
    )
}
@Composable
fun CustomPhoneField(
    phone: String,
    onPhoneChange:(String) -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        OutlinedTextField(
            value = "+254",
            onValueChange = onPhoneChange,
            modifier = Modifier
                .width(80.dp)
                .border(border = BorderStroke( 2.dp, MaterialTheme.colorScheme.primary)),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .border(border = BorderStroke( 2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)))
                .fillMaxWidth(),
            value = phone,
            onValueChange = onPhoneChange,
            label = {Text("Enter Your Tel Phone")},
            placeholder = { Text("Phone") },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            )
        )
    }
}

@Composable
fun CustomCheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier
){
 Row (verticalAlignment = Alignment.CenterVertically,
     modifier = modifier
         .fillMaxWidth()
 ){
     Checkbox(
         checked = checked,
         onCheckedChange = onCheckedChange,
         colors = CheckboxDefaults.colors(
             checkedColor = MaterialTheme.colorScheme.primary,
             checkmarkColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f )
         )
     )
     Spacer(modifier = Modifier.width(8.dp))
     Text(
         text = label,
         style = MaterialTheme.typography.titleMedium
     )
  }
}
@Composable
fun CustomTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    TextButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
    ) {
        Text(
            text = text,
            modifier = modifier.padding(16.dp),
            color = Color.White
        )
    }
}
@Preview
@Composable
fun ContactFormPreview() {
    ContactForm()
}

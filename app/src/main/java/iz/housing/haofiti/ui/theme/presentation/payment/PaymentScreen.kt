package iz.housing.haofiti.ui.theme.presentation.payment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PaymentMethodsScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Payment methods", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Choose desired vehicle type. We offer cars suitable for most everyday needs.", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(listOf("Mastercard", "Visa", "PayPal")) { method ->
                PaymentMethodItem(method)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("CURRENT METHOD", fontSize = 12.sp, color = Color.Gray)
        PaymentMethodItem("Cash payment", isDefault = true)

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /* TODO: Handle add payment method */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF9800))
        ) {
            Text("ADD PAYMENT METHOD", color = Color.White)
        }
    }
}

@Composable
fun PaymentMethodItem(method: String, isDefault: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // TODO: Add proper icons for payment methods
        Text(method, modifier = Modifier.weight(1f))
        if (isDefault) {
            Text("Default method", color = Color(0xFFFF9800))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentMethodsScreenPreview() {
    PaymentMethodsScreen()
}
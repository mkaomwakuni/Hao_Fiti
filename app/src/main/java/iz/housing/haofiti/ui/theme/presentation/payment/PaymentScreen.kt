package iz.housing.haofiti.ui.theme.presentation.payment
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import iz.housing.haofiti.R

data class PaymentMethod(
    val type: String,
    val icon: Int
)

@Composable
fun PaymentMethodsScreen() {
    val paymentMethods = listOf(
        PaymentMethod("Mastercard", R.drawable.mastercard),
        PaymentMethod("Visa", R.drawable.visa),
        PaymentMethod("PayPal", R.drawable.paypal),
        PaymentMethod("Mpesa", R.drawable.mpesa),
        PaymentMethod("Airtel Money", R.drawable.airtel)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 50.dp)
    ) {
        // Top bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Close",
                modifier = Modifier.size(24.dp)
            )
            Text("Payment methods", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Done", color = Color(0xFFFF9800))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Choose desired vehicle type. We offer cars suitable for most everyday needs.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Payment methods list
        LazyColumn {
            items(paymentMethods) { method ->
                PaymentMethodItem(method)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("CURRENT SELECTED METHOD", fontSize = 12.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        CurrentMethodItem()

        Spacer(modifier = Modifier.weight(1f))

        // Add payment method button
        Button(
            onClick = { /* TODO: Navigate to Add Payment Method screen */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
        ) {
            Text("ADD PAYMENT METHOD", color = Color.White)
        }
    }
}

@Composable
fun PaymentMethodItem(method: PaymentMethod) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = method.icon),
            contentDescription = "${method.type} icon",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = method.type,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CurrentMethodItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.mpesa),
            contentDescription = "Payment icon",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text("M-pesa", fontWeight = FontWeight.Bold)
            Text("Default method", color = Color(0xFFFF9800), fontSize = 12.sp)
        }
        Icon(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = "Select",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentMethodsScreenPreview() {
    PaymentMethodsScreen()
}

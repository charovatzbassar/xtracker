import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xtracker.model.TransactionType

@Composable
fun TotalCard(title:String, amount: Double?, displayAmount: String, onClick: () -> Unit){
    val totalNumberColor: Color = when (title) {
        TransactionType.INCOME.type, TransactionType.SAVINGS.type -> MaterialTheme.colorScheme.primary
        TransactionType.EXPENSES.type -> MaterialTheme.colorScheme.error
        else -> Color.White
    }

    val totalNumberText: String = when (title) {
        TransactionType.INCOME.type, TransactionType.SAVINGS.type -> "+$$displayAmount"
        TransactionType.EXPENSES.type -> "-$$displayAmount"
        else -> displayAmount
    }

    val icon: ImageVector = when (title) {
        TransactionType.INCOME.type -> Icons.Default.AddCircle
        TransactionType.EXPENSES.type -> Icons.Default.ShoppingCart
        TransactionType.SAVINGS.type -> Icons.Default.Email
        else -> Icons.Default.AddCircle
    }

    Card(
        modifier = Modifier
            .height(110.dp)
            .width(150.dp)
            .padding(5.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Icon(imageVector = icon, contentDescription = "Icon")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = title, color = Color.DarkGray, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = totalNumberText, color = totalNumberColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}
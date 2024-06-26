import android.app.Dialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.xtracker.model.TransactionType
import com.example.xtracker.model.models.Transaction
import com.example.xtracker.viewModel.TransactionDetails
import com.example.xtracker.viewModel.TransactionViewModel
import com.example.xtracker.viewModel.toTransaction
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionCard(transaction : TransactionDetails?, transactionViewModel: TransactionViewModel, navController: NavHostController){
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(transaction?.date, formatter)
    var deleteModalOpen = remember {
        mutableStateOf(false)
    }

    val totalText = if (transaction?.type == TransactionType.EXPENSES.type) {
        "-$${transaction.amount}"
    } else {
        "+$${transaction?.amount.toString()}"
    }

    val category: String = if (transaction?.type == TransactionType.EXPENSES.type) {
        " (${transaction.category})"
    } else ""


    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(350.dp)
            .height(160.dp)
    ){
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top=10.dp, bottom = 5.dp, end=10.dp)
        ){
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = "${date.dayOfMonth}.${date.month.value}.${date.year}", fontSize = 15.sp, fontStyle = FontStyle.Italic)
                Text(text = transaction?.type.toString() + category, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(20.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = totalText,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = when (transaction?.type) {
                            TransactionType.INCOME.type, TransactionType.SAVINGS.type -> MaterialTheme.colorScheme.primary
                            TransactionType.EXPENSES.type -> MaterialTheme.colorScheme.error
                            else -> MaterialTheme.colorScheme.primary
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

        }
        Row (modifier = Modifier.padding(top=5.dp, bottom = 5.dp,start=5.dp, end=10.dp)) {

            TextButton(onClick = {
                navController.navigate("edit/${transaction!!.transactionID}")
            }) {
                Text(text = "Edit", color = MaterialTheme.colorScheme.primary)
            }
            TextButton(onClick = {
                deleteModalOpen.value = true
            }) {
                Text(text = "Delete", color = MaterialTheme.colorScheme.error)
            }
        }
    }
    if (deleteModalOpen.value) {
        Dialog(onDismissRequest = { deleteModalOpen.value = false }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    
                    Text(text = "Are you sure you want to delete this transaction?")

                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        val delTransaction = transaction?.toTransaction()
                        transactionViewModel.deleteTransaction(delTransaction!!)
                        deleteModalOpen.value = false
                    }) {
                        Text("OK")
                    }
                }
            }
        }
    }
}

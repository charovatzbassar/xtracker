import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xtracker.model.models.Transaction
import com.example.xtracker.viewModel.TransactionDetails
import com.example.xtracker.viewModel.TransactionViewModel
import com.example.xtracker.viewModel.toTransaction
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionCard(transaction : TransactionDetails?, transactionViewModel: TransactionViewModel){
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(transaction?.date, formatter)

    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(350.dp)
            .height(110.dp)
    ){
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top=5.dp, bottom = 5.dp,start=5.dp, end=10.dp)
        ){
            /*
            Image(
                painter = painterResource(id = //ovdje ide slika),
                contentDescription = "NESTO",
                    modifier = Modifier.size(100.dp)
                    contentScale = ContentScale.Crop
            )
            */
            Spacer(modifier = Modifier.width(20.dp))
            Column{
                Text(text = transaction?.type.toString())
                Spacer(modifier = Modifier.height(20.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = transaction?.amount.toString() + "USD",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = if(transaction?.amount!! < 0){
                            Color.Red
                        }
                        else{
                            Color.Green
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "${date.month.value.toString()}.${date.dayOfMonth.toString()}.${date.year.toString()}", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
            Button(onClick = {
                val delTransaction = transaction?.toTransaction()
                transactionViewModel.deleteTransaction(delTransaction!!)
            }) {
                Text(text = "Delete")
            }
        }
    }
}

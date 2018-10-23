package cubex.mahesh.sqlitedbtest_oct9am

import android.content.ContentValues
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dBase = openOrCreateDatabase("emp_db",
                Context.MODE_PRIVATE,null)
        dBase.execSQL("create table if not exists employee(_id integer primary key autoincrement,id integer,name varchar(500),desig varchar(500),dept varchar(500) )")


        insert.setOnClickListener {
    // long insert(String table, String nullColumnHack, ContentValues values)
            
            var cv = ContentValues( )
            cv.put("id",et1.text.toString().toInt())
            cv.put("name",et2.text.toString())
            cv.put("desig",et3.text.toString())
            cv.put("dept",et4.text.toString())
            var status = dBase.insert("employee",null, cv)
            if(status == -1L)
            {
                    Toast.makeText(this@MainActivity,
                            "Data Insertion is Failed",
                            Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity,
                        "Data Insertion is Success",
                        Toast.LENGTH_LONG).show()
                et1.setText(""); et2.setText("");et3.setText("");et4.setText("");
            }
        }
        read.setOnClickListener {
            /* Cursor query(String table, String[] columns, String selection,
            String[] selectionArgs, String groupBy, String having, String orderBy) */
  /*   var c = dBase.query("employee",
             arrayOf("_id","id","name","desig","dept"),null,
             null,null,null,null) */
    /*        var c = dBase.query("employee",
                    null,"id=? and dept=?",
                    arrayOf(et1.text.toString(),et4.text.toString()),
                    null,null,null) */
    /*        var c = dBase.query("employee",
                    null,null,
                    null,
                    "id","id>=125",null) */
            var c = dBase.query("employee",
                    null,null,
                    null,
                    null,null,"id desc")

/* SimpleCursorAdapter(Context context, int layout,
Cursor c, String[] from, int[] to, int flags)             */
        var cAdapter = SimpleCursorAdapter(this@MainActivity,
                R.layout.indiview,c, arrayOf("id","name","desig","dept"),
                intArrayOf(R.id.tv1,R.id.tv2,R.id.tv3,R.id.tv4),0)
            lview.adapter = cAdapter
        }
        update.setOnClickListener {
/* int update(String table, ContentValues values,
 String whereClause, String[] whereArgs)*/
            var cv = ContentValues( )
            cv.put("name",et2.text.toString())
            cv.put("desig",et3.text.toString())
            var status =  dBase.update("employee",cv,
                            "id=?", arrayOf(et1.text.toString()))
            if(status == 0)
            {
                Toast.makeText(this@MainActivity,
                        "Data Updation is Failed",
                        Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity,
                        "Data Updation is Success",
                        Toast.LENGTH_LONG).show()
                et1.setText(""); et2.setText("");et3.setText("");et4.setText("");
            }
        }
        delete.setOnClickListener {
    // int delete(String table, String whereClause, String[] whereArgs)
    /*    var status =   dBase.delete("employee","id=?",
                            arrayOf(et1.text.toString()))
            if(status == 0)
            {
                Toast.makeText(this@MainActivity,
                        "Data Deletion is Failed",
                        Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity,
                        "Data Deletion is Success",
                        Toast.LENGTH_LONG).show()
                et1.setText(""); et2.setText("");et3.setText("");et4.setText("");
            } */
            dBase.execSQL(
                    "delete from employee where id=${et1.text.toString()}")
        }
    }
}

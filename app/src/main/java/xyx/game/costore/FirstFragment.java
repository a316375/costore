package xyx.game.costore;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

import de.klimek.scanner.OnDecodedCallback;
import de.klimek.scanner.ScannerView;
import xyx.game.costore.OBJ.DaoMaster;
import xyx.game.costore.OBJ.DaoSession;
import xyx.game.costore.OBJ.Product;
import xyx.game.costore.OBJ.ProductDao;


public class FirstFragment extends Fragment   {


    private SQLiteDatabase writableDatabase;
    private DaoSession daoSession;
    private ProductDao usersDao;


    private Product product;
    private EditText name;
    private EditText price;
    private TextView textView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);


                if (product==null)return;
                if (name.getText().toString().length()>0){
                    product.setName(name.getText().toString());
                    Update(product);
                    textView.setText("更新成功");
                    name.setText(null);
                    product=null;

                }
                if (price.getText().toString().length()>0){
                    product.setPrice(String.valueOf(price.getText())+"元/条");
                    Update(product);
                    textView.setText("更新成功");
                    price.setText(null);
                    product=null;
                }


            }
        });

        name = view.findViewById(R.id.name);
        price = view.findViewById(R.id.price);
        textView = view.findViewById(R.id.textview_first);

        ScannerView scanner = (ScannerView)view. findViewById(R.id.scanner);
        scanner.setOnDecodedCallback(new OnDecodedCallback() {
            @Override
            public void onDecoded(String decodedData) {
                //Toast.makeText(getContext(), decodedData, Toast.LENGTH_SHORT).show();

                textView.setText(decodedData);
                Product query = Query(decodedData);
                if (query==null){Insert(decodedData);}else {
                    textView.setText(query.getCode()+"\n名称："+query.getName()+"\n价格："+query.getPrice());
                    product=query;
                }
            }
        });
        scanner.startScanning();
       // scanner.stopScanning();




        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "mydatabase", null);
        writableDatabase = helper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        daoSession = daoMaster.newSession();
        usersDao = daoSession.getProductDao();



        textView.setText("所有记录="+usersDao.loadAll().size());




    }


private Product Query(String code){
//        String email = "jdoe@example.com";
//    String firstname = "John";
// Single user query WHERE email matches "jdoe@example.com"
    Product user = usersDao.queryBuilder()
            .where(ProductDao.Properties.Code.eq(code)).build().unique();
// Multiple user query WHERE firstname = "John"
//    List<Product> user = usersDao.queryBuilder()
//            .where(ProductDao.Properties.Code.eq(firstname)).build().list();

    return user;
    }

private void Insert(String code){
        Product newUser = new Product(null,code,"暂无","0/条");
    usersDao.insert(newUser);}
private void Update(Product product){// Modify a previously retrieved user object and update
    //user.setLastname("Dole");
    usersDao.update(product);
    }
private void Delete(Product product){// Delete a previously retrieved user object
    usersDao.delete(product);}


//    @Override
//    protected void onStart() {
//        super.onStart();
//        qrCodeView.startCamera();//打开后置摄像头开始预览，但是并未开始识别
//        qrCodeView.startSpotAndShowRect(); // 显示扫描框，并开始识别
//
////        mQRCodeView.showScanRect();//显示扫描框
////        mQRCodeView.startSpot();//开始识别二维码
//        //mQRCodeView.openFlashlight();//开灯
//        //mQRCodeView.closeFlashlight();//关灯
//    }
//
//    @Override
//    protected void onStop() {
//        qrCodeView.stopCamera();// 关闭摄像头预览，并且隐藏扫描框
//        super.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        qrCodeView.onDestroy();
//        super.onDestroy();
//    }




}
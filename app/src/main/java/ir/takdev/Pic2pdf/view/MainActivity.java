package ir.takdev.Pic2pdf.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import ir.takdev.Pic2pdf.App;
import ir.takdev.Pic2pdf.R;
import ir.takdev.Pic2pdf.Utility.Actions;
import ir.takdev.Pic2pdf.Utility.MyDialogInterface;
import ir.takdev.Pic2pdf.viewmodel.PDFListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements MyDialogInterface, NavigationView.OnNavigationItemSelectedListener{
    public static final String EXTRA_PDF_ID = "EXTRA_PDF_ID";
    BottomSheetFragment bottomFragment;
    private PDFListViewModel mViewModel;
    private RecyclerView recyclerView;
    private PDFListAdapter adapter;
    private DrawerLayout mDrawerLayout;
    private boolean isMultiselect =false;
    private int choosed=0;
    NavigationView navigationView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        initRecyclerView();
        initViewModel();
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.btn_exit: {
                                confirm_close();
                                break;
                            }
                        }
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        menuItem.setChecked(false);

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        return true;
                    }
                });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioDialog_newpdf();
            }
        });

    }
    private void bindViews(){
        try {
            // addNewPdf=findViewById(R.id.new_pdf);
/*            addNewPdf.setOnClickListener((v -> {
                Intent intent=new Intent(this,NewpdfActivity.class);
                startActivity(intent);
            }));*/
        }
        catch (Exception ex){

        }
    }
    private void initRecyclerView() {
        recyclerView = findViewById(R.id.pdfs_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void initViewModel() {
        adapter = new PDFListAdapter(this);
        adapter.setOnClickListener((view, position) -> {
            if (isMultiselect){
                chooseMode();
            }
            else {
                long tmp=adapter.getItemId(position);
                showButtonSheet(adapter.getItemId(position));
            }

        });
        recyclerView.setAdapter(adapter);
        mViewModel = ViewModelProviders.of(this).get(PDFListViewModel.class);
        mViewModel.getPDFs().observe(this, (adapter::update));
    }

    private void chooseMode() {
        adapter.setMultiselect(true);
        //
    }

    public void showButtonSheet(long id) {
        bottomFragment = new BottomSheetFragment();
        Bundle bundle=new Bundle();
        bundle.putLong("pdf_id",id);
        bottomFragment.setArguments(bundle);
        bottomFragment.setAction(this);
        bottomFragment.show(getSupportFragmentManager(), bottomFragment.getTag());
    }
    public void deletePDF() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setContentText("از حذف فایل اطمینان دارید؟!")
                .setCancelText("انصراف")
                .setConfirmText("حذف")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        bottomFragment.deletePDF();
                        bottomFragment.dismiss();
/*                        sweetAlertDialog
                                .setTitleText("حذف شد!")
                                .setContentText("فایل با موفقیت حذف شد!")
                                .setConfirmText("خب")
                                .setConfirmClickListener(null)
                                .showCancelButton(false)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);*/
                        View contextView = findViewById(R.id.drawer_layout);
                        Snackbar snackbar = Snackbar.make(contextView, "فایل حذف شد.", Snackbar.LENGTH_LONG);
                        ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
                        snackbar.show();
                        sweetAlertDialog.cancel();
                    }
                })
                .show();

    }
    @Override
    public void action(Actions actions) {
        try {

            switch (actions) {
                case Edit:
                    Toast.makeText(this, "edit", Toast.LENGTH_LONG).show();
                    break;
                case View:
                    Toast.makeText(this, "", Toast.LENGTH_LONG).show();
                    break;
                case Delete:
                    deletePDF();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.customemenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.END);
                return true;
            case R.id.btn_filter:
                RadioDialog_filter();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    public void RadioDialog_newpdf() {
        // Toast.makeText(App.context,"Hello",Toast.LENGTH_LONG).show();

        String[] singleChoiceItems = getResources().getStringArray(R.array.dialog_single_choice_array);
        int itemSelected =0;
        choosed=itemSelected;
        AlertDialog.Builder builder = new AlertDialog.Builder(
                MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view=inflater.inflate(R.layout.rtl_title_newpdf, null);
        builder.setCustomTitle(view)
                .setSingleChoiceItems(new ArrayAdapter<String>(this,
                                R.layout.rtl_list_item,
                                R.id.text, singleChoiceItems),
                        0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                choosed =which;
                            }
                        });
        builder.setNegativeButton("انصراف",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                });
        builder.setPositiveButton("تایید",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        switch (choosed){
                            case 0:
                                break;
                            case 1:
                                break;
                            case 2:
                                Intent i=new Intent(MainActivity.this,NewpdfActivity.class);
                                startActivity(i);
                                break;
                            default:
                                break;
                        }
                    }
                });
        builder.show();
        choosed=0;
    }
    public void RadioDialog_filter() {
        String[] singleChoiceItems = getResources().getStringArray(R.array.dialog_filter);
        int selected =0;
        choosed=selected;
        AlertDialog.Builder builder = new AlertDialog.Builder(
                MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view=inflater.inflate(R.layout.rtl_title_filter, null);
        builder.setCustomTitle(view)
                .setSingleChoiceItems(new ArrayAdapter<String>(this,
                                R.layout.rtl_list_item,
                                R.id.text, singleChoiceItems),
                        0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                choosed =which;
                            }
                        });
        builder.setNegativeButton("انصراف",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                });
        builder.setPositiveButton("تایید",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(MainActivity.this,choosed+"",Toast.LENGTH_LONG).show();
                        switch (choosed){
                            case 0:
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            default:
                                break;
                        }
                    }
                });
        builder.show();
        choosed=0;
    }
    public void confirm_close() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setContentText("از برنامه خارج می شوید ؟")
                .setCancelText("انصراف")
                .setConfirmText("تایید")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .show();
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;


}

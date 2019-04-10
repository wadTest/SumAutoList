package com.prospec.sumautolist;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

//RecyclerView List
public class AdapterMain extends RecyclerView.Adapter<AdapterMain.DataObjectHolder> {
    private static MyClickListener myClickListener;
    private ArrayList<ModelMain> modelMains;

    public AdapterMain(ArrayList<ModelMain> modelMains) {
        this.modelMains = modelMains;
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        AdapterMain.myClickListener = myClickListener;
    }

    @Override
    public int getItemCount() {
        return modelMains.size();
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main_list, parent, false);

        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, final int position) {
        holder.listenerService.updatePosition(position);
        holder.listenerPrice.updatePosition(position);

        holder.edService.setText(modelMains.get(position).getService());

        holder.edPrice.setText("" + modelMains.get(position).getPrice());
        if (modelMains.get(position).isBtnPlus()){
            holder.btnAdd.setText("+");
            holder.btnAdd.setBackground(holder.itemView.getContext().getResources().getDrawable(R.drawable.button_design_plus));
        }else {
            holder.btnAdd.setText("-");
            holder.btnAdd.setBackground(holder.itemView.getContext().getResources().getDrawable(R.drawable.button_design_minus));
        }
    }

    public void addItem(ModelMain modelMain, int index) {
        modelMains.add(modelMain);
        if (index !=0 ){
            modelMains.get(index-1).setBtnPlus(false);
        }
        notifyDataSetChanged();
    }

    public void deleteItem(int index) {
        if (modelMains.size() > index){
            modelMains.remove(index);
            notifyDataSetChanged();
            totalPrice();
        }
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    private class MyCustomEditTextListener implements TextWatcher {

        private int position;
        private EditText editText;

        public MyCustomEditTextListener(EditText ed) {
            this.editText = ed;
        }
        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            if (charSequence.length() == 0){
                return;
            }

            if (editText.getId() == R.id.txtPrice){
                if (String.valueOf(modelMains.get(position).getPrice()).equals(charSequence.toString())){
                    return;
                }else {
                    modelMains.get(position).setPrice(Integer.parseInt(charSequence.toString()));
                    totalPrice();
                }
            }else if (editText.getId() == R.id.txtService){
                if (modelMains.get(position).getService().equals(charSequence.toString())){
                    return;
                }else {
                    modelMains.get(position).setService(charSequence.toString());
                }
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private void totalPrice() {
        int price=0;
        for (int j = 0;j<modelMains.size();j++){
            price+=modelMains.get(j).getPrice();
        }

        MainActivity.txtTotalPrice.setText(""+price);
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        EditText edService, edPrice;
        Button btnAdd;
        LinearLayout linBtn;
        MyCustomEditTextListener listenerPrice,listenerService;

        public DataObjectHolder(View itemView) {
            super(itemView);
            edService = (EditText) itemView.findViewById(R.id.txtService);
            edPrice = (EditText) itemView.findViewById(R.id.txtPrice);
            btnAdd = (Button) itemView.findViewById(R.id.btnAdd);
            linBtn = (LinearLayout)itemView.findViewById(R.id.linBtn);
            linBtn.setOnClickListener(this);
            btnAdd.setOnClickListener(this);
            this.listenerPrice = new MyCustomEditTextListener(this.edPrice);
            this.listenerService = new MyCustomEditTextListener(this.edService);
            this.edPrice.addTextChangedListener(listenerPrice);
            this.edService.addTextChangedListener(listenerService);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getLayoutPosition(), v);
        }
    }
}
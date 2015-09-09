package apdroid.clinica.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import apdroid.clinica.R;
import apdroid.clinica.entidades.DatosCita;

/**
 * Created by ANTONIO on 08/09/2015.
 */
public class RVDatosCitasAdapter extends RecyclerView.Adapter<RVDatosCitasAdapter.RVDatosCitasAdapterViewHolder> {

    private ArrayList<DatosCita> lstDatosCitas;

    public RVDatosCitasAdapter(ArrayList<DatosCita> datosCitas){
        lstDatosCitas = datosCitas;
    }

    @Override
    public RVDatosCitasAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        return new RVDatosCitasAdapterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lst_item_datoscita, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RVDatosCitasAdapterViewHolder lvDatosCitasAdapterViewHolder, int position) {
        DatosCita datosCita = lstDatosCitas.get(position);



    }

    @Override
    public int getItemCount() {
        return lstDatosCitas!=null?lstDatosCitas.size():0;
    }


    /**
     * HOLDER
     */
    static class RVDatosCitasAdapterViewHolder extends RecyclerView.ViewHolder {

        public RVDatosCitasAdapterViewHolder(View itemView) {
            super(itemView);
        }
    }

}

package com.example.dm2.aplicacionconfragmentos;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by dm2 on 10/11/2017.
 */

public class FragmentListado extends Fragment {

    private SubHeroe subHeroes[] = new SubHeroe[]{
            new SubHeroe("El rey leon", "Disney", "Un leon que se hace rey", "--", "--"),
            new SubHeroe("La milla verde", "anonimo", "Muchos locos y el tio del naufrago", "--", "--"),
            new SubHeroe("El bueno, el feo y el malo", "anonimo", "Tres personas que matan gente.", "--", "--")
    };

    SubHeroesListener listener;
    ListView lstListado;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {

        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        lstListado = (ListView) getView().findViewById(R.id.lstSubheroe);
        lstListado.setAdapter(new AdaptadorSubheroes(this));
        lstListado.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    listener.onSubheroeSeleccionado((SubHeroe) lstListado.getAdapter().getItem(position));
                }
            }
        });
    }

    class AdaptadorSubheroes extends ArrayAdapter<SubHeroe> {
        Activity context;


        AdaptadorSubheroes(Fragment context) {
            super(context.getActivity(), R.layout.listitem_subheroe, subHeroes);
            this.context = context.getActivity();
        }

        public View getView(int pos, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_subheroe, null);

            TextView lblNombre = (TextView) item.findViewById(R.id.lblNombre);
            lblNombre.setText(subHeroes[pos].getNombre());

            TextView lblNombreReal = (TextView) item.findViewById(R.id.lblNombreReal);
            lblNombreReal.setText(subHeroes[pos].getNombreReal());

            return (item);
        }


    }

    public interface SubHeroesListener{
        void onSubheroeSeleccionado(SubHeroe sh);
    }

    public void setSubHeroesListener (SubHeroesListener listener){
        this.listener=listener;
    }
}

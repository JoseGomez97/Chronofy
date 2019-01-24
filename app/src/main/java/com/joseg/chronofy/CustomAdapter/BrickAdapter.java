package com.joseg.chronofy.CustomAdapter;

/*
    Se encarga de adaptar el modelo del brick a la ListView
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.joseg.chronofy.DataModel.BrickModel;
import com.joseg.chronofy.R;

import java.util.ArrayList;

public class BrickAdapter extends ArrayAdapter<BrickModel> implements View.OnClickListener{

    private ArrayList<BrickModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView tituloBrick;
        TextView descripcionBrick;
        ImageView imagenOpcionesBrick;
    }

    public BrickAdapter(ArrayList<BrickModel> data, Context context) {
        super(context, R.layout.brick_view, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        final int position=(Integer) v.getTag();
        Object object= getItem(position);
        BrickModel brickModel=(BrickModel) object;

        switch (v.getId())
        {
            case R.id.imagen_opciones_brick:
                // Cuando pulsemos sobre las opciones del brick...
                PopupMenu popupMenu = new PopupMenu(getContext(), v);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // TODO Acción de cada uno de los botones.
                        switch (item.getItemId()) {
                            case R.id.reiniciar_brick:
                                Toast.makeText(getContext(), "Reiniciar",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.editar_brick:
                                Toast.makeText(getContext(), "Editar",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.eliminar_brick:
                                dataSet.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(getContext(), "Elemento " + position + " eliminado",
                                        Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.inflate(R.menu.brick);
                popupMenu.show();
                break;

            default:
                // Cuando pulsemos sobre un brick...
                Snackbar.make(v, "Brick pulsado", Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        BrickModel brickModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.brick_view, parent, false);
            viewHolder.tituloBrick = convertView.findViewById(R.id.titulo_brick);
            viewHolder.descripcionBrick = convertView.findViewById(R.id.descripcion_brick);
            viewHolder.imagenOpcionesBrick = convertView.findViewById(R.id.imagen_opciones_brick);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        //Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        //result.startAnimation(animation);
        lastPosition = position;

        viewHolder.tituloBrick.setText(brickModel.getName());
        viewHolder.descripcionBrick.setText(brickModel.getDescription());
        viewHolder.imagenOpcionesBrick.setOnClickListener(this);
        viewHolder.imagenOpcionesBrick.setTag(position);
        // Return the completed view to render on screen
        return result;
    }
}
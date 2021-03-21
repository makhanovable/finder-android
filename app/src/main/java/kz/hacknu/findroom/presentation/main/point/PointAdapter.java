package kz.hacknu.findroom.presentation.main.point;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import kz.hacknu.findroom.R;

/**
 * created by: Makhanov Madiyar
 * on: 20.03.2021
 */
public class PointAdapter implements GoogleMap.InfoWindowAdapter {
    private final LayoutInflater layoutInflater;
    public PointAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getInfoContents(Marker marker) {
        Object tag = marker.getTag();
        View v = layoutInflater.inflate(R.layout.i_info, null);

        if (tag != null && tag instanceof PointTag) {
            TextView title = v.findViewById(R.id.title);
            TextView deadline = v.findViewById(R.id.deadline);

            title.setText(((PointTag) tag).getTitle());
            deadline.setText("Available until:\n" + ((PointTag) tag).getEndDate());
        }
        return v;
    }
}

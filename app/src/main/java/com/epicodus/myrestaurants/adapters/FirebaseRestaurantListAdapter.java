package com.epicodus.myrestaurants.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.myrestaurants.models.Restaurant;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import com.epicodus.myrestaurants.util.ItemTouchHelperAdapter;
import com.epicodus.myrestaurants.util.OnStartDragListener;

/**
 * Created by minnehmugo on 11/06/2017.
 */

public class FirebaseRestaurantListAdapter extends FirebaseRecyclerAdapter<Restaurant, FirebaseRestaurantViewHolder>  implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseRestaurantListAdapter(Class<Restaurant> modelClass, int modelLayout,
                                         Class<FirebaseRestaurantViewHolder> viewHolderClass,
                                         Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

//    comes from an interface included as part of the FirebaseRecyclerAdapter class.
    @Override
    protected void populateViewHolder(final FirebaseRestaurantViewHolder viewHolder, Restaurant model, int position){
        viewHolder.bindRestaurant(model);
        viewHolder.mRestaurantImageView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

//onItemMove() and onItemDismiss() override methods from the ItemTouchHelperAdapter interface.
    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
//        make other list items move to give way on drag
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position){
//         delete the dismissed item from Firebase
        getRef(position).removeValue();
    }
}

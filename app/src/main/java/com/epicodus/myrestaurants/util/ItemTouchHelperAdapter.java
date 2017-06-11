package com.epicodus.myrestaurants.util;

/**
 * Created by minnehmugo on 11/06/2017.
 */

public interface ItemTouchHelperAdapter {
//    called each time the user moves an item by dragging
// it across the touch screen
    boolean onItemMove(int fromPosition, int toPosition);

//    called when an item has been dismissed with a swipe motion
    void onItemDismiss(int position);
}

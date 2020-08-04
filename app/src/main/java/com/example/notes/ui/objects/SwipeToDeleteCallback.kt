package com.example.notes.ui.objects

import android.app.Activity
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.database.models.Note
import com.example.notes.ui.NoteAdapter

class SwipeToDeleteCallback(
    adapter: NoteAdapter,
    activity: Activity,
    val onSwipedResult: (Note) -> Unit
) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val mAdapter: NoteAdapter = adapter
    private val mIcon: Drawable? = ContextCompat.getDrawable(activity, R.drawable.ic_delete)
    private val mBackground = ColorDrawable(Color.RED)

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView = viewHolder.itemView
        val backgroundCornerOffset = 20

        val iconMargin: Int = (itemView.height - mIcon!!.intrinsicHeight) / 2
        val iconTop: Int =
            itemView.top + (itemView.height - mIcon.intrinsicHeight) / 2
        val iconBottom: Int = iconTop + mIcon.intrinsicHeight

        if (dX < 0) { // Swiping to the left
            val iconLeft = itemView.right - iconMargin - mIcon.intrinsicWidth
            val iconRight = itemView.right - iconMargin;
            mIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)

            mBackground.setBounds(
                itemView.right + dX.toInt() - backgroundCornerOffset,
                itemView.top, itemView.right, itemView.bottom
            )
        } else { // view is unSwiped
            mBackground.setBounds(0, 0, 0, 0)
        }

        mBackground.draw(c)
        mIcon.draw(c)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onSwipedResult(mAdapter.getNoteByPosition(viewHolder.adapterPosition))
    }
}
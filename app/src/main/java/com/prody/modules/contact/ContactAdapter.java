package com.prody.modules.contact;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prody.R;
import com.prody.core.data.models.config.ContactItem;
import com.prody.core.data.models.config.Style;
import com.prody.core.di.InjectionHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * TODO: Class description
 *
 * @author Paul
 * @since 2017.12.25
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    @Inject
    Style mStyle;

    private final List<ContactItem> mContactItemList;

    public ContactAdapter(List<ContactItem> contactItemList) {
        for (int i = 0; i < contactItemList.size(); i++) {
            ContactItem contactItem = contactItemList.get(i);
            if (contactItem.getType() == null) {
                Timber.w("Found a menu item without type! " + contactItem);
                contactItemList.remove(i--);
            }
        }
        mContactItemList = contactItemList;
        InjectionHelper.getApplicationComponent().inject(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, mStyle);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ContactItem item = mContactItemList.get(position);
        Drawable drawable = ResourcesCompat.getDrawable(holder.itemView.getContext().getResources(), item.getType().getDrawable(), null);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, mStyle.getPrimary());
        holder.mIcon.setImageDrawable(drawable);
        holder.mText.setText(item.getText());
        holder.itemView.setClickable(item.getType().isClickable());
        holder.itemView.setFocusable(item.getType().isClickable());
        if (item.getType().isClickable()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickedItem(item, view.getContext());
                }
            });
        } else {
            holder.itemView.setOnClickListener(null);
        }
    }

    private void clickedItem(ContactItem item, Context context) {
        Intent intent = null;
        switch (item.getType()) {
            case EMAIL:
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + item.getText()));
                break;
            case PHONE:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + item.getText()));
                break;
            case LOCATION:
                if (item.getLatitude() != 0 && item.getLongitude() != 0) {
                    intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=" + item.getLatitude() + "," + item.getLongitude()));
                    break;
                }
        }

        if (intent != null && intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return mContactItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_contact_icon)
        ImageView mIcon;
        @BindView(R.id.item_contact_text)
        TextView mText;

        public ViewHolder(ViewGroup viewGroup, Style style) {
            super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contact, viewGroup, false));
            ButterKnife.bind(this, itemView);
            mText.setTextColor(style.getTextColorOnBackground());
        }
    }
}

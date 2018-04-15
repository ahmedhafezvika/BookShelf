package com.ahmedhafez.bookshelf.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmedhafez.bookshelf.R;
import com.ahmedhafez.bookshelf.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Eng.Ahmed on 4/15/2018.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookItemAdapterViewHolder> {

    private List<Book> mData;
    private final BooksAdapterOnClickHandler mClickHandler;
    private Context mContext;

    public interface BooksAdapterOnClickHandler {
        void onClick(Book book);
    }

    public BooksAdapter(Context context, BooksAdapterOnClickHandler clickHandler, List<Book> books) {
        mClickHandler = clickHandler;
        mData = books;
        mContext = context;
    }

    @Override
    public BookItemAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutItemId = R.layout.book_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutItemId, parent, shouldAttachToParentImmediately);
        return new BookItemAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookItemAdapterViewHolder holder, int position) {
        Book book = mData.get(position);
        String img_url = book.getVolumeInfo().getImageLinks().getThumbnail();

        ImageView poster = holder.thumbnail;

        Picasso.with(mContext)
                .load(img_url)
                .placeholder(R.drawable.poster_placeholder)
                .error(R.drawable.poster_placeholder)
                .into(poster);
        holder.title.setText(book.getVolumeInfo().getTitle());
    }

    @Override
    public int getItemCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    public void setBooksData(List<Book> books) {
        mData = books;
        notifyDataSetChanged();
    }

    public Book getBookByIndex (int index) {
        return mData.get(index);
    }

    public class BookItemAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView thumbnail;
        public final TextView title;

        public BookItemAdapterViewHolder(View view) {
            super(view);
            thumbnail = view.findViewById(R.id.thumbnail);
            title = view.findViewById(R.id.title);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Book book = mData.get(adapterPosition);
            mClickHandler.onClick(book);
        }
    }
}
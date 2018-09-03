package com.gsr.dummy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder> {

    public List<Questions> questionList;
    public QuestionListAdapter(List<Questions>questionList){
        this.questionList=questionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mQuestion.setText(questionList.get(position).getQuery());
        holder.mAnswer.setText(questionList.get(position).getAnswer());

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TextView mQuestion;
        public TextView mAnswer;

        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;

            mQuestion=mView.findViewById(R.id.question);
            mAnswer=mView.findViewById(R.id.answer);
        }
    }
}

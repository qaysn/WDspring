package com.example.todo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DetailFragment  extends Fragment {

    public TextView id;
    public EditText title;
    public EditText description;
    public EditText status;
    public TextView category;
    public EditText duration;
    private CategoryDao categoryDao = MyApplication.getInstance().getDatabase().categoryDao();
    private TodoDao todoDao = MyApplication.getInstance().getDatabase().todoDao();

    public static DetailFragment newInstance(Todo todo) {
        DetailFragment fragment = new DetailFragment();
        //с помощью Bundle мы передаем данные в фрагмент
        Bundle data = new Bundle();
        data.putString("id", todo.id + ""); //+" " чтобы он стал стрингом
        data.putString("title", todo.title);
        data.putString("description", todo.description);
        data.putString("status", todo.status);
        data.putString("duration", todo.duration);
        data.putInt("category", todo.categoryId);
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        final Button update = view.findViewById(R.id.update);
        //при нажатии кнопки апдэйт
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int todo_id = Integer.parseInt(id.getText().toString());
                //получаем тип строку по айдишке, с помощью метода описанное в тудуДао
                Todo todo = todoDao.getTodoById(todo_id);
                todo.title = title.getText().toString();
                todo.status = status.getText().toString();
                todo.description = description.getText().toString();
                todo.duration = duration.getText().toString();
                todoDao.update(todo);
                //тут мы реплэйсаем фрагменты, то есть открываем главный фрагмент с обновленными данными
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment, MainFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        final Button delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //тут тоже самое как в апдэйт только упрошенно
                int todo_id = Integer.parseInt(id.getText().toString());
                Todo todo = todoDao.getTodoById(todo_id);
                todoDao.delete(todo);
                //тут мы реплэйсаем фрагменты, то есть открываем главный фрагмент с обновленными данными
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment, MainFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //расположение наших вюшек
        id = view.findViewById(R.id.detail_id);
        title = view.findViewById(R.id.detail_title);
        description = view.findViewById(R.id.detail_description);
        status = view.findViewById(R.id.detail_status);
        category = view.findViewById(R.id.detail_category);
        duration = view.findViewById(R.id.detail_duration);
        //пробуем
        try {

            //getArguments() возвращает аргументы, переданные в setArguments (Bundle) и мы берем каждое по ключу
            //меняем эти данные вюшек
            id.setText(getArguments().getString("id"));
            title.setText(getArguments().getString("title"));
            description.setText(getArguments().getString("description"));
            status.setText(getArguments().getString("status"));
            duration.setText(getArguments().getString("duration"));
            //получаем сначало айдишку категории
            int category_id = getArguments().getInt("category");
            //потом по айдишке получаем тайтл и меняем данные категории
            category.setText(categoryDao.getCategoryById(category_id).title);
        }
        catch (Exception e) {
            Log.e("Error", e + " ");
        }

    }
}
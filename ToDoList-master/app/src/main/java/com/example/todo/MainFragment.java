package com.example.todo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainFragment extends Fragment {
    List<Todo> todoList;
    TodoDao todoDao;
    AppDatabase db;
    private Adapter.TodoItemClickListener listener = null;
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_list, container,false);
        RecyclerView recyclerView=view.findViewById(R.id.recycler);
        //наши айтемы будут распологаться линейно
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        db = MyApplication.getInstance().getDatabase();
        todoDao = db.todoDao();
        //возвращает все данные таблицы и мы присваеваем ее в лист
        todoList = todoDao.getAllTodo();
        //при нажатии на отдельный айтем "открывается" DetailFragment
        listener = new Adapter.TodoItemClickListener() {
            @Override
            public void itemClick(int position, Todo todo) {
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment, DetailFragment.newInstance(todo))
                        .addToBackStack("first")
                        .commit();
            }
        };

        Adapter myAdapter=new Adapter(todoList, listener);
        recyclerView.setAdapter(myAdapter);

        return view;
    }

}

package com.example.proje.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.proje.Adapter.CarAdapter;
import com.example.proje.Model.Car;
import com.example.proje.SessionManager.SessionManager;
import com.example.proje.ViewModel.CarViewModel;
import com.example.proje.databinding.FragmentCarListBinding;

public class CarListFragment extends Fragment {
    private FragmentCarListBinding binding;
    private CarViewModel carViewModel;
    private CarAdapter carAdapter;
    private SessionManager sessionManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentCarListBinding.inflate(inflater, container, false);
        binding.getRoot().setFitsSystemWindows(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        sessionManager = new SessionManager(requireContext());
        carViewModel = new ViewModelProvider(requireActivity()).get(CarViewModel.class);
        carAdapter = new CarAdapter();
        carAdapter.setOnDeleteClickListener(this::showDeleteConfirmationDialog);
        carAdapter.setCurrentUserName(sessionManager.getUsername()); // Burada kullanıcı adı setleniyor
        binding.recyclerViewCars.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewCars.setAdapter(carAdapter);

        carViewModel.getCars().observe(getViewLifecycleOwner(), cars -> {
            carAdapter.setCarList(cars);
        });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    private void showDeleteConfirmationDialog(Car car) {
        new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("İlanı Sil")
                .setMessage("Bu ilanı silmek istediğinize emin misiniz?")
                .setPositiveButton("Evet", (dialog, which) -> {
                    String currentUser = sessionManager.getUsername();
                    if (currentUser != null && currentUser.trim().equalsIgnoreCase(car.getName().trim())) {
                        carViewModel.deleteCar(car);
                    } else {
                        Toast.makeText(requireContext(), "Bu ilanı silme yetkiniz yok.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Hayır", null)
                .show();
    }
}

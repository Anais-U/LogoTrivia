package cl.bootcamp.logotrivia;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import cl.bootcamp.logotrivia.databinding.FragmentWelcomeBinding;

public class WelcomeFragment extends Fragment {

    private FragmentWelcomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout usando View Binding
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        // Configurar el botón "COMENZAR"
        binding.btncomenzar.setOnClickListener(v -> {
            String userName = binding.etname.getText().toString().trim();
            if (!userName.isEmpty()) {
                Bundle bundle = new Bundle();
                bundle.putString("userName", userName);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_welcomeFragment_to_questionFragment, bundle);
            } else {
                Toast.makeText(getContext(), "Por favor, ingresa tu nombre", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


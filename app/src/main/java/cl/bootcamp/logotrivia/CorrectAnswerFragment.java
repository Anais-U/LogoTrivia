package cl.bootcamp.logotrivia;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import cl.bootcamp.logotrivia.databinding.FragmentCorrectAnswerBinding;

public class CorrectAnswerFragment extends Fragment {

    private FragmentCorrectAnswerBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout usando View Binding
        binding = FragmentCorrectAnswerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener el nombre del usuario desde el Bundle
        String userName = getArguments() != null ? getArguments().getString("userName") : "Usuario";
        binding.tvcorrect.setText(getString(R.string.tvcorrect, userName));

        // Configurar el botón "INTÉNTALO OTRA VEZ"
        binding.btntryagain.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);

            // Crear un Bundle para pasar el nombre de usuario de vuelta a QuestionFragment
            Bundle bundle = new Bundle();
            bundle.putString("userName", userName);

            // Navegar de vuelta a QuestionFragment con el Bundle
            navController.navigate(R.id.action_correctAnswerFragment_to_questionFragment, bundle);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

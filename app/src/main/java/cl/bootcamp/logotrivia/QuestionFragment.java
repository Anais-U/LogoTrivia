package cl.bootcamp.logotrivia;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import cl.bootcamp.logotrivia.databinding.FragmentQuestionBinding;

public class QuestionFragment extends Fragment {

    private FragmentQuestionBinding binding;
    private String correctAnswer = "Pertenece a Whathsapp"; // Define la respuesta correcta aquí

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout usando View Binding
        binding = FragmentQuestionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener el nombre del usuario desde el Bundle
        String userName = getArguments() != null ? getArguments().getString("userName") : "Usuario";
        binding.tvSaludo.setText(getString(R.string.tvSaludo, userName));

        // Configurar el botón "ENVIAR"
        binding.btnenviar.setOnClickListener(v -> {
            int selectedOptionId = binding.rbOptions.getCheckedRadioButtonId();
            if (selectedOptionId != -1) {
                String selectedOption = ((RadioButton) binding.getRoot().findViewById(selectedOptionId)).getText().toString();
                NavController navController = Navigation.findNavController(view);

                // Crear un Bundle para pasar el nombre de usuario al siguiente fragmento
                Bundle bundle = new Bundle();
                bundle.putString("userName", userName);

                // Navegar a CorrectAnswerFragment o IncorrectFragment con el bundle
                if (selectedOption.equals(correctAnswer)) {
                    navController.navigate(R.id.action_questionFragment_to_correctAnswerFragment, bundle);
                } else {
                    navController.navigate(R.id.action_questionFragment_to_incorrectFragment, bundle);
                }
            } else {
                Toast.makeText(getContext(), "Por favor selecciona una opción", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

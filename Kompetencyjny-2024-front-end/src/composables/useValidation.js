import { watch } from 'vue';
import { usePredictionStore } from '@/store/PredictionStore';

export const useValidation = (validationRules) => {
  const predictionStore = usePredictionStore();

  const validateParameter = (paramName) => {
    watch(() => predictionStore.parameters[paramName], (newValue) => {
      if (newValue < validationRules[paramName].min) {
        predictionStore.parameters[paramName] = validationRules[paramName].min;
      } else if (newValue > validationRules[paramName].max) {
        predictionStore.parameters[paramName] = validationRules[paramName].max;
      }
      predictionStore.parameters[paramName] = Number(predictionStore.parameters[paramName]);
    });
  };

  Object.keys(validationRules).forEach(validateParameter);
};

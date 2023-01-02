package com.httt.backend.service;

import com.httt.backend.repository.QuestionTypeRepository;
import com.httt.backend.ultilities.EQTypeCode;
import com.httt.backend.entity.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {

    private QuestionTypeRepository questionTypeRepository;

    @Autowired
    public QuestionTypeServiceImpl(QuestionTypeRepository questionTypeRepository) {
        this.questionTypeRepository = questionTypeRepository;
    }

    @Override
    public Optional<QuestionType> getQuestionTypeById(Long id) {
        return questionTypeRepository.findById(id);
    }

    @Override
    public Optional<QuestionType> getQuestionTypeByCode(EQTypeCode code) {
        return questionTypeRepository.findAllByTypeCode(code);
    }

    @Override
    public List<QuestionType> getQuestionTypeList() {
        return questionTypeRepository.findAll();
    }

    @Override
    public void saveQuestionType(QuestionType questionType) {
        questionTypeRepository.save(questionType);
    }

    @Override
    public void delete(Long id) {
        questionTypeRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return questionTypeRepository.existsById(id);
    }
}

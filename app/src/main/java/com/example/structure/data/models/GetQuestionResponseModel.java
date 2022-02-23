package com.example.structure.data.models;

import java.util.List;

/**
 * Created by Rajesh Pradeep G on 04-11-2019
 */
public class GetQuestionResponseModel {

    /**
     * status : 1
     * success : Questions
     * questions : [{"id":1,"question":"What is your favorite movie?","created_at":null,"updated_at":null},{"id":2,"question":"What was your favorite food as a child?","created_at":null,"updated_at":null},{"id":3,"question":"In what town was your first job?","created_at":null,"updated_at":null},{"id":4,"question":"What is your favorite team?","created_at":null,"updated_at":null}]
     */

    private String status;
    private String success;
    private String error;
    private List<QuestionsBean> questions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<QuestionsBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsBean> questions) {
        this.questions = questions;
    }

    public static class QuestionsBean {
        /**
         * id : 1
         * question : What is your favorite movie?
         * created_at : null
         * updated_at : null
         */

        private int id;
        private String question;
        private Object created_at;
        private Object updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public Object getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Object created_at) {
            this.created_at = created_at;
        }

        public Object getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Object updated_at) {
            this.updated_at = updated_at;
        }
    }
}

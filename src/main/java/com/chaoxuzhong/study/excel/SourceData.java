package com.chaoxuzhong.study.excel;

import lombok.Data;

@Data
public class SourceData {
        private String company;
        private String language;
        private String createTime;
        private String id;
        private String languageForUs;
        private Long priority;
        private String englishSource;
        private String changeVolumn;
        private String phase;
        private String sourceTitle;
        private String description; // 必须有东西
        private String stringId;
        private String number;
        private String hasAttachments;// yes needs

        public String getCompany() {
                return company;
        }

        public void setCompany(String company) {
                this.company = company;
        }

        public String getLanguage() {
                return language;
        }

        public void setLanguage(String language) {
                this.language = language;
        }

        public String getCreateTime() {
                return createTime;
        }

        public void setCreateTime(String createTime) {
                this.createTime = createTime;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getLanguageForUs() {
                return languageForUs;
        }

        public void setLanguageForUs(String languageForUs) {
                this.languageForUs = languageForUs;
        }

        public Long getPriority() {
                return priority;
        }

        public void setPriority(Long priority) {
                this.priority = priority;
        }

        public String getEnglishSource() {
                return englishSource;
        }

        public void setEnglishSource(String englishSource) {
                this.englishSource = englishSource;
        }

        public String getChangeVolumn() {
                return changeVolumn;
        }

        public void setChangeVolumn(String changeVolumn) {
                this.changeVolumn = changeVolumn;
        }

        public String getPhase() {
                return phase;
        }

        public void setPhase(String phase) {
                this.phase = phase;
        }

        public String getSourceTitle() {
                return sourceTitle;
        }

        public void setSourceTitle(String sourceTitle) {
                this.sourceTitle = sourceTitle;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getStringId() {
                return stringId;
        }

        public void setStringId(String stringId) {
                this.stringId = stringId;
        }

        public String getNumber() {
                return number;
        }

        public void setNumber(String number) {
                this.number = number;
        }

        public String getHasAttachments() {
                return hasAttachments;
        }

        public void setHasAttachments(String hasAttachments) {
                this.hasAttachments = hasAttachments;
        }
}

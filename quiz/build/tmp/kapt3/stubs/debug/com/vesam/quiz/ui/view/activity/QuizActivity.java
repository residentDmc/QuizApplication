package com.vesam.quiz.ui.view.activity;

import java.lang.System;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00ba\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020\u0019H\u0002J\u000e\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00190.H\u0002J\b\u0010/\u001a\u000200H\u0002J\u0010\u00101\u001a\u0002002\u0006\u00102\u001a\u00020\'H\u0002J\u0010\u00103\u001a\u0002002\u0006\u00104\u001a\u000205H\u0002J\u0010\u00106\u001a\u0002002\u0006\u00104\u001a\u000205H\u0002J\u0010\u00107\u001a\u0002002\u0006\u00104\u001a\u000205H\u0002J\b\u00108\u001a\u000200H\u0002J\u0010\u00109\u001a\u0002002\u0006\u00104\u001a\u000205H\u0002J(\u0010:\u001a\u00020*2\u0006\u0010,\u001a\u00020\u00192\u0016\u0010;\u001a\u0012\u0012\u0004\u0012\u00020<0&j\b\u0012\u0004\u0012\u00020<`(H\u0002J\u0010\u0010=\u001a\u0002002\u0006\u0010>\u001a\u00020?H\u0002J\u0018\u0010@\u001a\u0002002\u0006\u0010A\u001a\u00020\'2\u0006\u0010>\u001a\u00020?H\u0002J\b\u0010B\u001a\u000200H\u0002J\u0018\u0010C\u001a\u0002002\u0006\u0010D\u001a\u00020\u00062\u0006\u0010E\u001a\u00020\u0006H\u0003J\u0010\u0010F\u001a\u0002002\u0006\u0010A\u001a\u00020?H\u0003J\u0018\u0010G\u001a\u0002002\u0006\u0010A\u001a\u00020\'2\u0006\u0010>\u001a\u00020?H\u0002J\u0010\u0010H\u001a\u0002002\u0006\u0010>\u001a\u00020?H\u0002J\u0010\u0010I\u001a\u00020\u00192\u0006\u00102\u001a\u00020\'H\u0002J\b\u0010J\u001a\u000200H\u0002J\b\u0010K\u001a\u000200H\u0002J\b\u0010L\u001a\u000200H\u0002J\b\u0010M\u001a\u000200H\u0002J\u0010\u0010N\u001a\u0002002\u0006\u0010A\u001a\u00020?H\u0003J\b\u0010O\u001a\u000200H\u0002J\b\u0010P\u001a\u000200H\u0002J\u0012\u0010Q\u001a\u0002002\b\u0010A\u001a\u0004\u0018\u00010RH\u0003J\b\u0010S\u001a\u000200H\u0002J\u0010\u0010T\u001a\u0002002\u0006\u0010>\u001a\u00020?H\u0002J\u0010\u0010U\u001a\u0002002\u0006\u0010V\u001a\u00020WH\u0002J\u0010\u0010X\u001a\u0002002\u0006\u0010V\u001a\u00020WH\u0002J\u0010\u0010Y\u001a\u0002002\u0006\u0010V\u001a\u00020WH\u0002J\u0010\u0010Z\u001a\u0002002\u0006\u0010V\u001a\u00020WH\u0002J\u0010\u0010[\u001a\u0002002\u0006\u0010V\u001a\u00020WH\u0002J\u0010\u0010\\\u001a\u0002002\u0006\u0010A\u001a\u00020?H\u0002J\b\u0010]\u001a\u000200H\u0002J\u0010\u0010^\u001a\u0002002\u0006\u0010_\u001a\u00020`H\u0002J\u0018\u0010a\u001a\u0002002\u0006\u0010A\u001a\u00020\'2\u0006\u0010>\u001a\u00020?H\u0002J\u0010\u0010b\u001a\u0002002\u0006\u0010A\u001a\u00020cH\u0002J\b\u0010d\u001a\u000200H\u0002J\b\u0010e\u001a\u000200H\u0002J\b\u0010f\u001a\u000200H\u0002J\u0010\u0010g\u001a\u0002002\u0006\u0010A\u001a\u00020hH\u0002J\u0012\u0010i\u001a\u0002002\b\u0010j\u001a\u0004\u0018\u00010kH\u0014J\b\u0010l\u001a\u000200H\u0014J-\u0010m\u001a\u0002002\u0006\u0010n\u001a\u00020\u00062\u000e\u0010o\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00190\u00182\u0006\u0010_\u001a\u00020`H\u0016\u00a2\u0006\u0002\u0010pR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u001aR\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001f\u0010\u000e\u001a\u0004\b\u001d\u0010\u001eR\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b$\u0010\u000e\u001a\u0004\b\"\u0010#R\u001e\u0010%\u001a\u0012\u0012\u0004\u0012\u00020\'0&j\b\u0012\u0004\u0012\u00020\'`(X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006q"}, d2 = {"Lcom/vesam/quiz/ui/view/activity/QuizActivity;", "Lcom/vesam/quiz/utils/base/BaseActivity;", "()V", "binding", "Lcom/vesam/quiz/databinding/ActivityQuizBinding;", "counterFile", "", "downloadId", "dummy", "handelErrorTools", "Lcom/vesam/quiz/utils/tools/HandelErrorTools;", "getHandelErrorTools", "()Lcom/vesam/quiz/utils/tools/HandelErrorTools;", "handelErrorTools$delegate", "Lkotlin/Lazy;", "navController", "Landroidx/navigation/NavController;", "progressBarStatus", "quizViewModel", "Lcom/vesam/quiz/ui/viewmodel/QuizViewModel;", "getQuizViewModel", "()Lcom/vesam/quiz/ui/viewmodel/QuizViewModel;", "quizViewModel$delegate", "requiredPermissions", "", "", "[Ljava/lang/String;", "throwableTools", "Lcom/vesam/quiz/utils/tools/ThrowableTools;", "getThrowableTools", "()Lcom/vesam/quiz/utils/tools/ThrowableTools;", "throwableTools$delegate", "toastTools", "Lcom/vesam/quiz/utils/tools/ToastTools;", "getToastTools", "()Lcom/vesam/quiz/utils/tools/ToastTools;", "toastTools$delegate", "urlList", "Ljava/util/ArrayList;", "Lcom/vesam/quiz/data/model/file_download/FileDownload;", "Lkotlin/collections/ArrayList;", "checkAndRequestPermissions", "", "getAllFileInStorage", "name", "getNeededPermissions", "", "initAction", "", "initAddList", "fileDownload", "initAnswerFormatAudio", "answer", "Lcom/vesam/quiz/data/model/quiz_detail/Answer;", "initAnswerFormatVideo", "initAnswerResult", "initBundle", "initCheckDescription", "initCheckFile", "files", "Ljava/io/File;", "initCheckFinal", "responseQuizDetailModel", "Lcom/vesam/quiz/data/model/quiz_detail/ResponseQuizDetailModel;", "initCheckList", "it", "initClozeQuiz", "initCounter", "count", "final", "initDelayQuizDetailModel", "initDownloadComplete", "initDownloadFile", "initFileDownload", "initFinish", "initHideLoading", "initHideProgressAndCounterLoading", "initHideProgressLoading", "initLoading", "initMultimediaQuiz", "initNavController", "initOnProgress", "Lcom/downloader/Progress;", "initPermissions", "initProcessing", "initQuestionList", "question", "Lcom/vesam/quiz/data/model/quiz_detail/Question;", "initQuestions", "initQuestionsFormatAudio", "initQuestionsFormatVideo", "initQuestionsResult", "initQuizDetailModel", "initRequest", "initRequestCode", "grantResults", "", "initResultDownload", "initResultQuiz", "", "initShowLoading", "initShowProgressLoading", "initSingleHideProgressLoading", "initThrowable", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onRequestPermissionsResult", "requestCode", "permissions", "(I[Ljava/lang/String;[I)V", "quiz_debug"})
public final class QuizActivity extends com.vesam.quiz.utils.base.BaseActivity {
    private com.vesam.quiz.databinding.ActivityQuizBinding binding;
    private androidx.navigation.NavController navController;
    private final kotlin.Lazy toastTools$delegate = null;
    private final kotlin.Lazy throwableTools$delegate = null;
    private final kotlin.Lazy handelErrorTools$delegate = null;
    private final kotlin.Lazy quizViewModel$delegate = null;
    private final java.util.ArrayList<com.vesam.quiz.data.model.file_download.FileDownload> urlList = null;
    private int progressBarStatus = 0;
    private int counterFile = 0;
    private int dummy = 0;
    private int downloadId = 0;
    private final java.lang.String[] requiredPermissions = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    
    private final com.vesam.quiz.utils.tools.ToastTools getToastTools() {
        return null;
    }
    
    private final com.vesam.quiz.utils.tools.ThrowableTools getThrowableTools() {
        return null;
    }
    
    private final com.vesam.quiz.utils.tools.HandelErrorTools getHandelErrorTools() {
        return null;
    }
    
    private final com.vesam.quiz.ui.viewmodel.QuizViewModel getQuizViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initPermissions() {
    }
    
    private final boolean checkAndRequestPermissions() {
        return false;
    }
    
    private final java.util.List<java.lang.String> getNeededPermissions() {
        return null;
    }
    
    private final void initAction() {
    }
    
    private final void initBundle() {
    }
    
    private final void initNavController() {
    }
    
    private final void initRequest() {
    }
    
    private final void initShowLoading() {
    }
    
    private final void initHideLoading() {
    }
    
    private final void initShowProgressLoading() {
    }
    
    private final void initHideProgressLoading() {
    }
    
    private final void initHideProgressAndCounterLoading() {
    }
    
    private final void initSingleHideProgressLoading() {
    }
    
    private final void initResultQuiz(java.lang.Object it) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void initDelayQuizDetailModel(com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel it) {
    }
    
    private final void initQuestions(com.vesam.quiz.data.model.quiz_detail.Question question) {
    }
    
    private final void initQuestionsResult(com.vesam.quiz.data.model.quiz_detail.Question question) {
    }
    
    private final void initQuestionsFormatAudio(com.vesam.quiz.data.model.quiz_detail.Question question) {
    }
    
    private final void initAddList(com.vesam.quiz.data.model.file_download.FileDownload fileDownload) {
    }
    
    private final java.lang.String initFileDownload(com.vesam.quiz.data.model.file_download.FileDownload fileDownload) {
        return null;
    }
    
    private final boolean getAllFileInStorage(java.lang.String name) {
        return false;
    }
    
    private final boolean initCheckFile(java.lang.String name, java.util.ArrayList<java.io.File> files) {
        return false;
    }
    
    private final void initQuestionsFormatVideo(com.vesam.quiz.data.model.quiz_detail.Question question) {
    }
    
    private final void initQuestionList(com.vesam.quiz.data.model.quiz_detail.Question question) {
    }
    
    private final void initCheckDescription(com.vesam.quiz.data.model.quiz_detail.Answer answer) {
    }
    
    private final void initAnswerResult(com.vesam.quiz.data.model.quiz_detail.Answer answer) {
    }
    
    private final void initAnswerFormatVideo(com.vesam.quiz.data.model.quiz_detail.Answer answer) {
    }
    
    private final void initAnswerFormatAudio(com.vesam.quiz.data.model.quiz_detail.Answer answer) {
    }
    
    private final void initDownloadFile(com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel responseQuizDetailModel) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void initLoading(com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel it) {
    }
    
    private final void initProcessing(com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel responseQuizDetailModel) {
    }
    
    private final void initResultDownload(com.vesam.quiz.data.model.file_download.FileDownload it, com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel responseQuizDetailModel) {
    }
    
    private final void initFinish() {
    }
    
    private final void initDownloadComplete(com.vesam.quiz.data.model.file_download.FileDownload it, com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel responseQuizDetailModel) {
    }
    
    private final void initCheckFinal(com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel responseQuizDetailModel) {
    }
    
    private final void initCheckList(com.vesam.quiz.data.model.file_download.FileDownload it, com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel responseQuizDetailModel) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void initOnProgress(com.downloader.Progress it) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void initCounter(int count, int p1_48718011) {
    }
    
    private final void initThrowable(java.lang.Throwable it) {
    }
    
    private final void initQuizDetailModel(com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel it) {
    }
    
    private final void initClozeQuiz() {
    }
    
    private final void initMultimediaQuiz() {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    private final void initRequestCode(int[] grantResults) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public QuizActivity() {
        super();
    }
}
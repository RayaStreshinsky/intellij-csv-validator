package net.seesharpsoft.intellij.plugins.csv.intention;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import net.seesharpsoft.intellij.plugins.csv.CsvHelper;
import net.seesharpsoft.intellij.plugins.csv.psi.CsvField;
import net.seesharpsoft.intellij.plugins.csv.psi.CsvTypes;
import net.seesharpsoft.intellij.psi.PsiHelper;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@NonNls
public class CsvQuoteValueIntentionAction extends CsvIntentionAction {

    public CsvQuoteValueIntentionAction() {
        super("Quote");
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, @Nullable final PsiElement psiElement) {
        if (!super.isAvailable(project, editor, psiElement)) {
            return false;
        }

        PsiElement element = psiElement == null ? null : CsvHelper.getParentFieldElement(psiElement);
        return element instanceof CsvField &&
                element.getFirstChild() != null &&
                (PsiHelper.getElementType(element.getFirstChild()) != CsvTypes.QUOTE ||
                        PsiHelper.getElementType(element.getLastChild()) != CsvTypes.QUOTE);
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {
        CsvIntentionHelper.quoteValue(project, CsvHelper.getParentFieldElement(element));
    }

}
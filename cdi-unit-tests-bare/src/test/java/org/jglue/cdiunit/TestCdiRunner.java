package org.jglue.cdiunit;


import org.junit.Assert;
import org.junit.Test;
import org.junit.cdiunit.NonTestClass;
import org.junit.runner.RunWith;
import jakarta.inject.Inject;

@RunWith(CdiRunner.class)
@AdditionalClasses(AImplementation1.class)
public class TestCdiRunner {

	@Inject
	private NonTestClass nonTestClass;

    @Inject
    AInterface a;

    @Test
    public void testStart() {
        Assert.assertNotNull(a);
        Assert.assertNotNull(nonTestClass);
        nonTestClass.a();
    }

}
